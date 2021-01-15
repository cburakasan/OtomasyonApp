package com.asan.otomasyon.DersOgrenci.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OgrenciDersSecmesiRequestDto implements Serializable {

    private Long dersId;
    private Long ogrenciId;
}
