package com.miao.constant;

/**
 * @author Dhx_
 * @className RedisContant
 * @description TODO
 * @date 2022/10/22 16:50
 */
public class RedisConstant {
    public static final String LOGIN_CODE_KEY= "login:code:";
    public static final String LOGIN_TOKEN_KEY = "login:token:";
    public static final String TOPIC_TAG_KEY = "topic:tag:";
    public static final String LOCK_ANIMAL_KEY= "lock:animal";
    public static final  Long CACHE_ANIMAL_TTL=30L;
    public static final Long CACHE_TOKEN_TTL = 30L;
    public static final String ANIMAL_CACHE_KEY = "cache:animal:";
    public static final Long CACHE_NULL_TTL = 2L;
    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long CACHE_SHOP_TTL = 30L;
    public static final Long LOCK_SHOP_TTL = 10L;
    public static final String TOPIC_COMMENT_KEY = "topic:comment";
    public static final String REPLY_COMMENT_KEY = "reply:comment";
}
