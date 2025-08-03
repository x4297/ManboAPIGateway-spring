package io.github.x4297.manboapigateway.utils.messagesender;


import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;


public record MessageSender(
    String appid,
    String secret,
    String url
){
    public MessageSenderResp send(String mobile, String content){
        return RestClient.create(url).post()
                .header("appid", appid)
                .header("secret", this.secret)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new MessageSenderReq(mobile, content))
                .retrieve().body(MessageSenderResp.class);
    }
}
