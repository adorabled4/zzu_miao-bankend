package com.miao.util;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Dhx_
 * @className RedisData
 * @description TODO
 * @date 2022/10/28 20:36
 */
@Data
public class RedisData {
    /**
     * 逻辑过期时间
     */
    private LocalDateTime expireTime;

    /**
     *
     * */
    private Object data;
}
