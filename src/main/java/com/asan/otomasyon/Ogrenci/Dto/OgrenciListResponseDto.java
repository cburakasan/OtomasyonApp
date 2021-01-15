package com.asan.otomasyon.Ogrenci.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OgrenciListResponseDto implements Serializable {

  private  List<OgrenciDto> ogrenciDtoList;

  private String mesaj;
}
