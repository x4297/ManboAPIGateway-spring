package io.github.x4297.manboapigateway.model;


import lombok.Data;

import java.time.LocalDateTime;


@Data
public class OperateLog{
    private Long id;
    private String method;
    private String input;
    private String output;
    private String error;
    private LocalDateTime dateTime;
}
