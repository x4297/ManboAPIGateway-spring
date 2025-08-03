package io.github.x4297.manboapigateway.dto.ret;


import java.util.List;


public record App1RetDto(
        List<String> successList,
        List<String> failedList
){
}
