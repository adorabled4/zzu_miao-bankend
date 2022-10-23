package com.miao.common;

/**
 * @author Dhx_
 * @className ErrorCode
 * @description TODO
 * @date 2022/10/23 21:56
 */
public enum ErrorCode {
    SUCCESS(0,"ok",""),
    //HTTP状态码本身就是500，为什么500，因为你的业务里面抛异常 , 但是不应该让前端出现500，因为我们刚刚自己定义了一个业务异常，它应该返回40000
    PARAMS_ERROR(40000,"请求参数为空",""),
    NULL_ERROR(40001,"请求数据为空",""),
    NOT_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(500000,"系统内部异常","")
            ;
    final int code;
    final String message;
    final String description;
    ErrorCode(int code, String message , String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
