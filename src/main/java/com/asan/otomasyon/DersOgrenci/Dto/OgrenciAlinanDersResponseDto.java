package com.asan.otomasyon.DersOgrenci.Dto;

import com.asan.otomasyon.Ders.Dto.DersDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OgrenciAlinanDersResponseDto implements Serializable {

    private List<DersDto> dersDtoList;
    private String mesaj;


}
