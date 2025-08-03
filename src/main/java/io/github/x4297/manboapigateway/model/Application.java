package io.github.x4297.manboapigateway.model;


import com.baomidou.mybatisplus.annotation.TableName;


@TableName("application")
public record Application(
        String appid,
        String secret
){
}
