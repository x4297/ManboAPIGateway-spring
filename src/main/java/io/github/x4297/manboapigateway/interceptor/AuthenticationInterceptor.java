package io.github.x4297.manboapigateway.interceptor;


import io.github.x4297.manboapigateway.exception.AuthenticationFailedException;
import io.github.x4297.manboapigateway.mapper.ApplicationMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor{
    private final ApplicationMapper mapper;

    @Autowired
    public AuthenticationInterceptor(ApplicationMapper mapper){
        this.mapper = mapper;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        var xappid = request.getHeader("X-Appid");
        var xsecret = request.getHeader("X-Secret");

        if(xappid == null || xsecret == null){
            throw new AuthenticationFailedException("appid and secret is null");
        }

        var result = mapper.selectByMap(Map.of("appid", xappid));
        if(result.size() != 1){
            throw new AuthenticationFailedException("appid or secret is incorrect");
        }

        var app = result.getFirst();

        if(!xsecret.equals(app.secret())){
            throw new AuthenticationFailedException("appid or secret is incorrect");
        }

        return true;
    }
}
