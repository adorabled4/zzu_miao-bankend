package com.miao.interceptor;

import com.miao.util.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dhx_
 * @className LoginInterceptor
 * @description TODO
 * @date 2022/10/22 16:34
 */
public class LoginInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public LoginInterceptor(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate=stringRedisTemplate;
    }

    public LoginInterceptor(){}

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 判断是否需要去拦截(TheadLocal 中是否有用户)
        if(UserHolder.getUser()==null) {
            // 没有 , 需要拦截
            response.setStatus(401);
            //拦截
            return false;
        }
        //有用户 , 放行
        return true;
    }
}
