package com.asan.otomasyon.Ders.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DersEkleRequestDto implements Serializable {

    private String dersAdi;
    private Long kontenjan;

}
