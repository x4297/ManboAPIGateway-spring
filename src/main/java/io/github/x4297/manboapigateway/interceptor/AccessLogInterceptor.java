package io.github.x4297.manboapigateway.interceptor;


import io.github.x4297.manboapigateway.mapper.AccessLogMapper;
import io.github.x4297.manboapigateway.model.AccessLog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.util.HashMap;


@Order(1)
@Component
public class AccessLogInterceptor implements HandlerInterceptor{
    private final AccessLogMapper mapper;

    @Autowired
    public AccessLogInterceptor(AccessLogMapper mapper){
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        String javaMethod = "";
        if(handler instanceof HandlerMethod hm){
            javaMethod = String.join(".", hm.getBeanType().getName(), hm.getMethod().getName());
        }

        var log = new AccessLog();
        log.setMethod(request.getMethod());
        log.setUrl(String.join("?", request.getRequestURL(), request.getQueryString()));
        log.setSip(request.getRemoteAddr());
        log.setStatusCode(response.getStatus());
        log.setDateTime(LocalDateTime.now());
        log.setJavaMethod(javaMethod);

        StringBuilder ress = new StringBuilder();

        var res = (HashMap<?, ?>)request.getAttribute("CUSTOM_REQSP");
        if(res != null){
            for(var entry : res.entrySet()){
                ress.append("##[").append(entry.getKey()).append(" -> ").append(entry.getValue()).append("]**");
            }
        }

        if(ex != null){
            ress.append(ex.getMessage());
        }

        log.setRes(ress.toString());

        mapper.insert(log);
    }
}
