package com.asan.otomasyon.DersOgrenci.Dto;

import com.asan.otomasyon.Ders.Dto.DersDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class OgrenciDersSecmesiResponseDto implements Serializable {

    private DersDto dersDto;
    private String mesaj;
}
