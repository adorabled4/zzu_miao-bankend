package com.miao.util;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.miao.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Dhx_
 * @className CacheClient
 * @description TODO
 * @date 2022/10/10 15:43
 */
@Slf4j
@Component
public class CacheClient {

    /**
     * 线程池, 避免反复创建\销毁线程, 造成资源的浪费
     */
    public static final ExecutorService CACHE_REBUILD_EXECUTOR= Executors.newFixedThreadPool(10);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public CacheClient(){}

    public void set(String key, Object value, Long time, TimeUnit unit){
        String jsonStr = JSONUtil.toJsonStr(value);
        stringRedisTemplate.opsForValue().set(key,jsonStr,time,unit);
    }

    /**
     * 设置逻辑过期 => 解决缓存击穿
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
        //设置逻辑过期
        RedisData redisData = new RedisData();
        redisData.setData(value);
        // 注意单位转换  ??? => seconds
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));

        String jsonStr = JSONUtil.toJsonStr(redisData);

        //写入redis
        stringRedisTemplate.opsForValue().set(key,jsonStr);
    }


    /**
     *
     * @param keyPrefix 前缀 => 例如 CACHE_SHOP_KEY
     * @param id       id => 根据id 来拼接 redis 的 key
     * @param type     对象的类型
     * @param dbFallBack 传入一段操作,  这里就是查询数据库的操作
     * @param time     保存时间 (TTL)
     * @param unit     时间单位
     * @param <R>      对象类型
     * @param <ID>     id的类型 , 可能以多种形式存储
     * @return
     */
    public <R,ID> R  queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID,R> dbFallBack, Long time, TimeUnit unit)  {
        String key=keyPrefix+id;
        //1. 从redis中查询商户缓存
        String json=stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if (StrUtil.isNotBlank(json)) {
            // 3.存在，直接返回
            R r=JSONUtil.toBean(json, type);
            return r;
        }
        if(json!=null){
            //如果Json 不为null 那么一定是一个空字符串 (可能是缓存穿透存储空对象)
            return null;
        }
        //4 不存在 根据ID查询数据库
        R r=dbFallBack.apply(id);
        //5.不存在 返回error
        if(r==null){
            //将空值写入redis => 避免缓存穿透
            stringRedisTemplate.opsForValue().set(key,"", RedisConstant.CACHE_NULL_TTL,TimeUnit.MINUTES);
            return null;
        }
        //6.存在, 写入redis
        this.set(key,JSONUtil.toJsonStr(r),time,unit);
        return r;
    }

    /**
     * 逻辑过期解决缓存击穿
     * @param id
     * @return
     */
    public <R,ID>  R queryWithLogicalExpire(String keyPrefix, ID id, Class<R> type, Function<ID,R> dbFallBack, Long time, TimeUnit unit){
        String key= keyPrefix+id;
        //1. 从redis中查询商户缓存 ,
        String json=stringRedisTemplate.opsForValue().get(key);
        //2判断是否存在
        if(StrUtil.isBlank(json)){
            //3不存在 , 返回null
            return null;
        }
        //4.命中,需要吧JSON反序列化为对象
        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        JSONObject data = (JSONObject) redisData.getData();
        R r = JSONUtil.toBean(data, type);
        LocalDateTime expireTime=redisData.getExpireTime();
        //5. 需要判断过期时间
        if(expireTime.isAfter(LocalDateTime.now())){
            //5.1未过期, 返回店铺信息
            return r;
        }
        //5.2已过期, 需要缓存重建
        String lockKey=RedisConstant.LOCK_SHOP_KEY+id;
        //6. 缓存重建
        //6.1获取互斥锁
        boolean isLock=tryLock(lockKey);
        //6.2判断是否获取锁成功
        if(isLock){
            //todo 6.3 成功 => 开启独立线程, 实现缓存重建
            //将空值写入redis => 避免缓存穿透
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try {
                    // 查数据库
                    R newR = dbFallBack.apply(id);
                    //写入redis 重建缓存
                    this.setWithLogicalExpire(key,newR,time,unit);
                }catch(Exception e){
                    throw new RuntimeException(e);
                }finally {
                    //释放锁
                    unLock(lockKey);
                }
            });
        }
        //6.4 失败 返回过期的店铺信息
        return r;
    }

    /**
     * 缓存击穿
     * @param id
     * @return
     */
    public <R,ID> R  queryWithMutex(String keyPrefix, ID id, Class<R> type, Function<ID,R> dbFallBack, Long time, TimeUnit unit) {
        String key= RedisConstant.ANIMAL_CACHE_KEY+id;
        //1. 从redis中查询商户缓存 ,
        String json=stringRedisTemplate.opsForValue().get(key+id);
        //2判断是否存在
        if(StrUtil.isNotBlank(json)){
            //3 存在 ==> 返回 Shop对象
            return JSONUtil.toBean(json,type);
        }
        if(json!=null){
            //如果shopJson 不为null 那么一定是一个空字符串,
            return null;
        }
        // 4.实现缓存重构
        //4.1获取互斥锁
        String lockKey=RedisConstant.LOCK_ANIMAL_KEY+id;
        R r=null;
        try {
            boolean isLock=tryLock(lockKey);
            //4.2判断是否获取成功
            if(!isLock){
                //4.3如果获取失败 , 那么就休眠并重试
                Thread.sleep(50);//休眠
                return queryWithMutex(keyPrefix, id,type,dbFallBack, time ,unit);
            }
            //4.4成功 , 根据id查询数据库
            r=dbFallBack.apply(id);
            //5.不存在 返回null
            if(r==null){
                //将空值写入redis => 避免缓存穿透
                stringRedisTemplate.opsForValue().set(key,"",RedisConstant.CACHE_NULL_TTL,TimeUnit.MINUTES);
                return null;
            }
            //6.存在, 写入redis
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(r),RedisConstant.CACHE_ANIMAL_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //7.释放互斥锁
            unLock(lockKey);
        }
        //8.返回
        return r;
    }

    /**
     * 通过 redis 的 string 类型的 setnx来设置锁
     * @param key 锁的key
     * @return
     * setnx 的返回值是0 1 , 0代表设置失败 , 1 代表设置成功
     *  RedisTemplate自动转换为了Boolean类型,
     *  那么对应到工程中, 也就是false代表设置失败, 1代表设置成功
     *  也就是说如果是返回false就是之前就已经上锁了, 1表示上锁成功.
     */
    private boolean tryLock(String key){
        // 设置锁的时间为10s
        Boolean flag= stringRedisTemplate.opsForValue().setIfAbsent(key, "1", RedisConstant.LOCK_SHOP_TTL, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag); //使用工具类转换成初始类型避免出现nullPointerException
    }

    /**
     * 解锁
     * @param key
     */
    private void unLock(String key){
        stringRedisTemplate.delete(key);
    }
}

