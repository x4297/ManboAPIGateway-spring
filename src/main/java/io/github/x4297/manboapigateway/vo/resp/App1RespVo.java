package io.github.x4297.manboapigateway.vo.resp;


import io.github.x4297.manboapigateway.utils.resp.Lengthable;
import lombok.Data;

import java.util.List;


@Data
public class App1RespVo implements Lengthable{
    private List<String> successList;
    private List<String> failedList;

    @Override
    public int length(){
        return successList.size() + failedList.size();
    }
}
