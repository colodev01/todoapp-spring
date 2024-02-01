package com.colodev.demo.exception;

import lombok.Getter;

@Getter
public class ToDoException extends RuntimeException {
    private int status;

    public ToDoException(int status, String message) {
        super(message);
        this.status = status;
    }

}