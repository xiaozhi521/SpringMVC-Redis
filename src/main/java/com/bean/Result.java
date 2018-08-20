package com.bean;

import java.io.Serializable;

public class Result implements Serializable {
    private int code;

    private String message;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }

        if (obj instanceof Result) {
            Result result = (Result)obj;
            return result.code == code && result.data == data && result.message == message;
        } else{
            return false;
        }
    }
}
