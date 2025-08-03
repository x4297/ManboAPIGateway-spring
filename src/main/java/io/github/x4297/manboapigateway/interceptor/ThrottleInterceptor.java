package io.github.x4297.manboapigateway.interceptor;


import io.github.x4297.manboapigateway.mapper.OperateLogMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.LimitExceededException;
import java.time.LocalDateTime;


@Order(3)
@Component
public class ThrottleInterceptor implements HandlerInterceptor{
    private final OperateLogMapper mapper;

    @Value("${limit.second}")
    private long second;

    @Value("${limit.times}")
    private int times;

    @Autowired
    public ThrottleInterceptor(OperateLogMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        var count = mapper.count(LocalDateTime.now().minusSeconds(second));

        if(count >= times){
            throw new LimitExceededException("request too many");
        }

        return true;
    }
}
