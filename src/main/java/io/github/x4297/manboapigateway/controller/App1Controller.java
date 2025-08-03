package io.github.x4297.manboapigateway.controller;

import io.github.x4297.manboapigateway.dto.call.App1CallDto;
import io.github.x4297.manboapigateway.service.App1Service;
import io.github.x4297.manboapigateway.utils.resp.Response;
import io.github.x4297.manboapigateway.vo.req.App1ReqVo;
import io.github.x4297.manboapigateway.vo.resp.App1RespVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class App1Controller{
    private final App1Service service;

    @Autowired
    public App1Controller(App1Service service){
        this.service = service;
    }

    @PostMapping("/app1")
    public ResponseEntity<?> sendMessage(@RequestBody @Validated App1ReqVo vo, HttpServletRequest request){
        var dto = new App1CallDto();
        BeanUtils.copyProperties(vo, dto, "appid", "secret");

        var retDto = service.sendMessage(dto);

        var respVo = new App1RespVo();
        BeanUtils.copyProperties(retDto, respVo);

        var map = new HashMap<>();

        map.put("req", vo);
        map.put("resp", respVo);

        request.setAttribute("CUSTOM_REQSP", map);

        if(retDto.failedList().isEmpty()){
            return ResponseEntity.ok(Response.ok(respVo));
        }
        else{
            return ResponseEntity.ok(Response.failed(respVo));
        }
    }
}
