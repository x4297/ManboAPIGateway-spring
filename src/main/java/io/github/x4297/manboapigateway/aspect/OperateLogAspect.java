package io.github.x4297.manboapigateway.aspect;


import io.github.x4297.manboapigateway.mapper.OperateLogMapper;
import io.github.x4297.manboapigateway.model.OperateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
public class OperateLogAspect{
    @Pointcut("execution(* io.github.x4297.manboapigateway.service.*.*(..))")
    public void pointCut(){}

    private final OperateLogMapper mapper;

    @Autowired
    public OperateLogAspect(OperateLogMapper mapper){
        this.mapper = mapper;
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object r;
        var log = new OperateLog();

        log.setMethod(joinPoint.getSignature().getDeclaringTypeName());
        log.setInput(Arrays.toString(joinPoint.getArgs()));

        try{
            r = joinPoint.proceed();
            log.setOutput(r.toString());
        }
        catch(Throwable e){
            log.setError(e.getMessage());
            throw e;
        }
        finally{
            log.setDateTime(LocalDateTime.now());
            mapper.insert(log);
        }

        return r;
    }
}
