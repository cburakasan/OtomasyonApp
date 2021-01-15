package com.asan.otomasyon.Ders.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DersGuncellemeRequestDto implements Serializable {

    private Long dersId;
    private String dersAdi;
    private Long kontenjan;


}
