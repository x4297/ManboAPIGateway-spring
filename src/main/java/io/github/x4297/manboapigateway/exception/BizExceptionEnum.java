package io.github.x4297.manboapigateway.exception;


import lombok.Getter;


public enum BizExceptionEnum{
    SEND_MESSAGE_FAILED(2, "Send Message Failed");

    @Getter
    private final int code;

    @Getter
    private final String message;

    BizExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
