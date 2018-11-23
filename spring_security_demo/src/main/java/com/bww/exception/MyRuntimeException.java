package com.bww.exception;

public class MyRuntimeException extends RuntimeException {

    private Integer id;

    public MyRuntimeException(Integer id) {
        super("user is wrong");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
