package com.asan.otomasyon.Ogrenci.Exception;

import lombok.Data;

@Data
public class OgrenciException extends Exception {
    private String message;

    public OgrenciException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }

}
