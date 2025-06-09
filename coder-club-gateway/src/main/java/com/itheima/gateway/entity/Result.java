package com.itheima.gateway.entity;


import com.itheima.gateway.enums.ResultCode;
import lombok.Data;

@Data
public class Result<T> {
    private Boolean success;

    private Integer code;

    private String message;

    private T data;

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getDesc());
        return result;
    }
    public static Result fail() {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(ResultCode.FAIL.getDesc());
        return result;
    }
    public static <T> Result ok(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getDesc());
        result.setData(data);
        return result;
    }
    public static <T> Result fail(T data) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(ResultCode.FAIL.getDesc());
        result.setData(data);
        return result;
    }
    public static Result fail(Integer code, String message) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
