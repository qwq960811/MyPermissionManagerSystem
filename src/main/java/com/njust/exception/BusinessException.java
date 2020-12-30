package com.njust.exception;

import com.njust.exception.code.ResponseCodeInterface;

public class BusinessException extends RuntimeException{

    private final int code;
    private final String msg;



    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ResponseCodeInterface responseCodeInterface){
        this(responseCodeInterface.getCode(), responseCodeInterface.getMsg());
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
