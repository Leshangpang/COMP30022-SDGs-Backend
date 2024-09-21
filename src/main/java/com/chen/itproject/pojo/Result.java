package com.chen.itproject.pojo;

import lombok.Data;

@Data
public class Result {
    int code;
    String msg;
    Object data;

    static public Result successResult(Object data) {
        Result result = new Result();
        result.setCode(1);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    static public Result errorResult() {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("error");
        result.setData(null);
        return result;
    }

    static public Result errorResult(String msg) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
