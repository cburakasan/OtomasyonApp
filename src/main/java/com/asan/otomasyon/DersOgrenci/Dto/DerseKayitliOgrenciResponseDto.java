package com.asan.otomasyon.DersOgrenci.Dto;

import com.asan.otomasyon.Ogrenci.Dto.OgrenciDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DerseKayitliOgrenciResponseDto implements Serializable {

    private List<OgrenciDto> ogrenciDtoList;
    private String mesaj;
}
