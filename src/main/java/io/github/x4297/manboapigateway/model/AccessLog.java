package io.github.x4297.manboapigateway.model;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;


@TableName
@Data
public class AccessLog{
    @TableId
    private Long id;
    private String method;
    private String url;
    private String sip;
    private int statusCode;
    private String res;
    private LocalDateTime dateTime;
    private String javaMethod;
}
