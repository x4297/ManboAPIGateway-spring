package io.github.x4297.manboapigateway.vo.req;


import io.github.x4297.manboapigateway.annotation.AllNotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;


@AllNotEmpty
public record App1ReqVo(
    @Pattern(regexp = "^\\d{11}(,\\d{11})*$")
    String mobile,

    @Length(max = 2048)
    String content,

    @Length(max = 256)
    String appid,

    @Length(max = 256)
    String secret
){}
