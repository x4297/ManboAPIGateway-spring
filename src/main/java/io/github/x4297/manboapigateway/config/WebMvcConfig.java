package io.github.x4297.manboapigateway.config;


import io.github.x4297.manboapigateway.interceptor.AccessLogInterceptor;
import io.github.x4297.manboapigateway.interceptor.AuthenticationInterceptor;
import io.github.x4297.manboapigateway.interceptor.ThrottleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    private final AccessLogInterceptor logInterceptor;
    private final AuthenticationInterceptor authInterceptor;
    private final ThrottleInterceptor throttleInterceptor;

    @Autowired
    public WebMvcConfig(AccessLogInterceptor logInterceptor,
                        AuthenticationInterceptor authInterceptor,
                        ThrottleInterceptor throttleInterceptor){
        this.logInterceptor = logInterceptor;
        this.authInterceptor = authInterceptor;
        this.throttleInterceptor = throttleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").order(2);
        registry.addInterceptor(throttleInterceptor).addPathPatterns("/**");
    }
}
