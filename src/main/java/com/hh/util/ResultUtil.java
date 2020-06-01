package com.hh.util;

import com.hh.enums.ResponseStatus;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ResultUtil<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code = ResponseStatus.OK.getCode();

    /**
     * 响应消息
     * */
    private String msg = ResponseStatus.OK.getMsg();
    /**
     * 响应中的数据
     * */
    private T data;

    private ResultUtil() {

    }

    private ResultUtil(ResponseStatus ResponseStatus) {
        this.code = ResponseStatus.getCode();;
        this.msg = ResponseStatus.getMsg();
    }

    private ResultUtil(T data) {
        this.data = data;
    }

    /**
     * 业务处理成功,无数据返回
     * */
    public static ResultUtil ok() {
        return new ResultUtil();
    }

    /**
     * 业务处理成功，有数据返回
     * */
    public static <T> ResultUtil ok(T data) {
        return new ResultUtil(data);
    }

    /**
     * 业务处理失败
     * */
    public static ResultUtil fail(ResponseStatus ResponseStatus) {
        return new ResultUtil(ResponseStatus);
    }


    /**
     * 系统错误
     * */
    public static ResultUtil error() {
        return new ResultUtil(ResponseStatus.ERROR);
    }
}


