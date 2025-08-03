package io.github.x4297.manboapigateway.exception;


public class RateLimitedException extends RuntimeException{
    public RateLimitedException(String message){
        super(message);
    }
}
