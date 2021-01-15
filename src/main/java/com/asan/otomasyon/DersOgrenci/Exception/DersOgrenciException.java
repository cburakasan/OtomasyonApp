package com.asan.otomasyon.DersOgrenci.Exception;

import lombok.Data;

@Data
public class DersOgrenciException extends Exception {

    private String message;

    public DersOgrenciException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

}
