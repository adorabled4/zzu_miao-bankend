package com.miao.Exception;

import com.miao.common.ErrorCode;
import lombok.Data;

/**
 * @author Dhx_
 * @className BusinessException  自定义业务异常类
 * @description TODO 支持更多字段,  更加灵活便捷
 * @date 2022/10/23 22:00
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private int code;

    /**
     * 错误描述
     */
    private String description;

    public BusinessException(int code,String description,String message){
        super(message);// 错误信息
        this.code=code;
        this.description=description;
    }

    public BusinessException(ErrorCode errorCode, String description){
        super(errorCode.getMessage());// 错误信息
        this.description=description;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());// 错误信息
        this.code=errorCode.getCode();
        this.description=errorCode.getDescription();
    }
}
