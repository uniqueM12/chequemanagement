package com.wizer.test.model;

/**
 * Created by Tenece on 19/08/2019.
 */


public class Error {

    public Error(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
