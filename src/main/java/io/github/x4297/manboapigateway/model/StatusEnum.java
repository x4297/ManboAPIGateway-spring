package io.github.x4297.manboapigateway.model;

public enum StatusEnum{
    SUCCESS("success"),
    FAILED("failed");

    private final String info;

    StatusEnum(String s){
        this.info = s;
    }

    public String toString(){
        return this.info;
    }
}
