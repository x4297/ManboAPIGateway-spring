package io.github.x4297.manboapigateway.service;


import io.github.x4297.manboapigateway.dto.call.App1CallDto;
import io.github.x4297.manboapigateway.dto.ret.App1RetDto;
import io.github.x4297.manboapigateway.exception.BizException;
import io.github.x4297.manboapigateway.exception.BizExceptionEnum;
import io.github.x4297.manboapigateway.utils.messagesender.MessageSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class App1Service{
    @Value("${message-sender.appid}")
    String appid;

    @Value("${message-sender.secret}")
    String secret;

    @Value("${message-sender.url}")
    String url;

    public App1RetDto sendMessage(App1CallDto dto){
        var sender = new MessageSender(appid, secret, url);

        var successList = new ArrayList<String>();
        var failedList = new ArrayList<String>();

        try{
            for(var mobile : dto.getMobile().split(",")){
                mobile = mobile.strip();
                var r = sender.send(mobile, dto.getContent());

                if(r.code() == 0){
                    successList.add(mobile);
                }
                else{
                    failedList.add(mobile);
                }
            }
        }
        catch(Exception e){
            throw new BizException(BizExceptionEnum.SEND_MESSAGE_FAILED);
        }

        return new App1RetDto(successList, failedList);
    }
}
