package com.xuan.practice.pojo;

import lombok.Data;

@Data
public class JsonResult<T> {

    private T data;
    private int code;
    private String msg;

    public JsonResult(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}

