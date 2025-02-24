package com.atguigu.common;

import lombok.Data;

@Data
public class R {

    private Integer code;
    private String msg;
    private Object data;

    public static R ok() {
        R r = new R();
        r.setCode(200);
        r.setMsg("success");
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setCode(200);
        r.setMsg("success");
        r.setData(data);
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(500);
        r.setMsg("error");
        return r;
    }

    public static R error(Integer code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
