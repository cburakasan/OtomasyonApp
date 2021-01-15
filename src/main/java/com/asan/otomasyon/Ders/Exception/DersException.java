package com.asan.otomasyon.Ders.Exception;

import lombok.Data;

@Data
public class DersException extends Exception {

    private String message;

    public DersException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

}
