package com.asan.otomasyon.DersOgrenci.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OgrenciDersSilRequestDto implements Serializable {

    private Long ogrenciId;
    private Long dersId;
}
