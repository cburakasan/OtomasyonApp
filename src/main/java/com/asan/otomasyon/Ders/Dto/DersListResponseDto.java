package com.asan.otomasyon.Ders.Dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DersListResponseDto implements Serializable {

  private  List<DersDto> dersDtoList;

  private String mesaj;
}
