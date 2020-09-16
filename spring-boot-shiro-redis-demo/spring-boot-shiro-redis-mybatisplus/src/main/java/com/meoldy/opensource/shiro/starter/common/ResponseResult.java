package com.meoldy.opensource.shiro.starter.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {

    private String code;
    private String msg;
    private Object data;

    public static ResponseResult ok(Object data) {
        ResponseResult result = new ResponseResult();
        result.setCode("0");
        result.setData(data);
        result.setMsg("操作成功");
        return result;
    }

    public static ResponseResult ok(String mess, Object data) {
        ResponseResult result = new ResponseResult();
        result.setCode("0");
        result.setData(data);
        result.setMsg(mess);

        return result;
    }

    public static ResponseResult fail(String mess) {
        ResponseResult result = new ResponseResult();
        result.setCode("-1");
        result.setData(null);
        result.setMsg(mess);

        return result;
    }

    public static ResponseResult fail(String mess, Object data) {
        ResponseResult result = new ResponseResult();
        result.setCode("-1");
        result.setData(data);
        result.setMsg(mess);

        return result;
    }
}