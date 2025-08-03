package io.github.x4297.manboapigateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("io.github.x4297.manboapigateway.mapper")
@EnableTransactionManagement
public class ManboApiGatewayApplication{

    public static void main(String[] args){
        SpringApplication.run(ManboApiGatewayApplication.class, args);
    }

}
