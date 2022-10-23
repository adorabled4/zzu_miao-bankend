package com.miao.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.miao.DTO.UserDTO;
import com.miao.constant.RedisConstant;
import com.miao.util.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Dhx_
 * @className RefreshTokenInterceptor
 * @description TODO
 * @date 2022/10/23 18:27
 */
public class RefreshTokenInterceptor implements HandlerInterceptor {
    StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(){}

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    1. 首先通过请求头获取token
        String token= request.getHeader("authorization");

//    2. 判断token是否为空
        if(StringUtils.isEmpty(token)){
            //    3. 如果为空就放行
            return true;
        }
//    4. 不为空=>刷新token在redis中的有效期
    //        1. 基于token 从redis中获取数据( HashMap类型)
        String key = RedisConstant.LOGIN_TOKEN_KEY+token;
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(key);
    //       2. 判断数据是否为空
        if(userMap.isEmpty()){
    //       3. 为空直接放行
            return true;
        }
    //        4. 如果不为空,  转换数据为UserDTO
        UserDTO userDTO = BeanUtil.fillBeanWithMap(userMap,new UserDTO(),false);
    //        5. 将UserDTO保存在ThreadLocal中
        UserHolder.saveUser(userDTO);
    //        6. 刷新数据在redis中的有效期=> 放行
        stringRedisTemplate.expire(key,RedisConstant.CACHE_TOKEN_TTL, TimeUnit.MINUTES);
        return true;
    }
}
