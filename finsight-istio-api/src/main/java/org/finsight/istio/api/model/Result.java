package org.finsight.istio.api.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private int code;
    private T data;
    private String message;
    private long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public Result(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    // 成功响应
    public static <T> Result<T> success(T data) {
        return new Result<>(200, data, "Success");
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(200, data, message);
    }

    // 失败响应
    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, null, message);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(500, null, message);
    }

    // 空数据响应
    public static <T> Result<T> empty() {
        return new Result<>(200, null, "No data found");
    }

    public static final int SUCCESS = 200;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
}