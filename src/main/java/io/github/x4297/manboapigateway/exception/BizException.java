package io.github.x4297.manboapigateway.exception;


import lombok.Getter;


public class BizException extends RuntimeException{
    @Getter
    private final int code;

    @Getter
    private final String message;

    public BizException(BizExceptionEnum bizEnum){
        super(bizEnum.getMessage());

        this.code = bizEnum.getCode();
        this.message = bizEnum.getMessage();
    }

    public BizException(BizExceptionEnum bizEnum, Exception cause){
        super(bizEnum.getMessage(), cause);

        this.code = bizEnum.getCode();
        this.message = bizEnum.getMessage();
    }


}
