package com.pet.test.javabean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yangzhengguang on 2018/9/13.
 */

public class BaseEntity<T> {
    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private T data;

    public boolean isSuccess() {
        return status == 0;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
