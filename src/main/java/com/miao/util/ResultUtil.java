package com.miao.util;

import com.miao.common.BaseResponse;
import com.miao.common.ErrorCode;

/**
 * @author Dhx_
 * @className ResultUtil
 * @description TODO
 * @date 2022/10/23 22:04
 */
public class ResultUtil {

    /**
     * 正常返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<T>(0,data,"ok","");
    }


    /**
     * 出现错误
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode){
        return new BaseResponse<T>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }

    /**
     *
     * @param errorCode
     * @param message 错误信息
     * @param description 描述
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     *
     * @param errorCode 错误码
     * @param description 描述
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode,String description){
        return new BaseResponse<T>(errorCode,description);
    }
}
