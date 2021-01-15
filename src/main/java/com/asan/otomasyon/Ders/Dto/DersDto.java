package com.asan.otomasyon.Ders.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DersDto implements Serializable {

    private Long DersId;

    private String dersinAdi;

    private Long kontenjan;

}
