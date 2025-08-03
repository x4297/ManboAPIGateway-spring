package io.github.x4297.manboapigateway.utils.resp;


public record Response<T>(
        int code,
        boolean success,
        String message,
        Result<T> result
){
    public record Result<T>(
        int total,
        T data
    ){
    }

    public static <T extends Lengthable> Response<T> ok(T data){
        return new Response<T>(0, true, "success",
                new Result<T>(data.length(), data));
    }

    public static <T extends Lengthable> Response<T> failed(T data){
        return new Response<T>(1, false, "failed",
                new Result<T>(data.length(), data));
    }
}
