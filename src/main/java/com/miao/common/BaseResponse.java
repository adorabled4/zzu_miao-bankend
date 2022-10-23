package com.miao.common;

import lombok.Data;

/**
 * @author Dhx_
 * @className BaseResponse
 * @description TODO
 * @date 2022/10/23 21:57
 */
@Data
public class BaseResponse<T> {
    private int code;

    private T data;//  controller 中的不同的方法返回值的类型不同

    private String message;

    private String description;

    public BaseResponse(int code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description=description;
    }
    public BaseResponse(int code, T data ,String description ) {
        this(code,data,"",description);
    }

    public BaseResponse(ErrorCode errorCode){
        this(errorCode.code,null,errorCode.message, errorCode.description);
    }

    public BaseResponse(ErrorCode errorCode,String description){
        this(errorCode.code,null,errorCode.message, description);
    }


    public BaseResponse(ErrorCode errorCode,String message,String description){
        this(errorCode.code,null,message, description);
    }
}
