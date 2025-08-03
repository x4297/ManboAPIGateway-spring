package io.github.x4297.manboapigateway.advice;


import io.github.x4297.manboapigateway.exception.AuthenticationFailedException;
import io.github.x4297.manboapigateway.exception.BizException;
import io.github.x4297.manboapigateway.utils.resp.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<?> authFailedHandle(AuthenticationFailedException e){
        var r = new Response<String>(
                3, false, e.getMessage(), null
        );

        return ResponseEntity.status(403).body(r);
    }

    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> BizExceptionHandler(BizException e){
        var r = new Response<String>(
                e.getCode(), false, e.getMessage(), null
        );

        return ResponseEntity.status(500).body(r);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> ValidateExceptionHandler(MethodArgumentNotValidException e){
        var result = e.getBindingResult();
        var map = new HashMap<String, String>();

        for(var error : result.getFieldErrors()){
            map.put(error.getField(), error.getDefaultMessage());
        }

        for(var error : result.getGlobalErrors()){
            map.put(error.getObjectName(), error.getDefaultMessage());
        }

        var r = new Response<>(4, false, "validate failed",
                new Response.Result<>(map.size(), map));

        return ResponseEntity.status(400).body(r);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> ThrowableHandler(Throwable e){
        var r = new Response<String>(5, false, e.getMessage(), null);

        return ResponseEntity.status(500).body(r);
    }
}
