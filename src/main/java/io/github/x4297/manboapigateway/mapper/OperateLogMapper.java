package io.github.x4297.manboapigateway.mapper;


import io.github.x4297.manboapigateway.model.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;


@Mapper
public interface OperateLogMapper{
    int insert(OperateLog one);

    int count(LocalDateTime since);
}
