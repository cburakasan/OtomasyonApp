package com.asan.otomasyon.Ogrenci.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OgrenciGuncellemeRequestDto implements Serializable {

    private Long ogrenciId;
    private String isim;
    private String soyisim;
    private Long ogrenciNo;


}
