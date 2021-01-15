package com.asan.otomasyon.Ogrenci.Service;

import com.asan.otomasyon.Ogrenci.Dto.*;

public interface OgrenciSercice {
    OgrenciListResponseDto getOgrenciList(OgrenciListRequestDto ogrenciListRequestDto);

    OgrenciEkleResponseDto ogrenciEkle(OgrenciEkleRequestDto ogrenciEkleRequestDto);

    OgrenciGuncellemeResponseDto ogrenciGuncelleme(OgrenciGuncellemeRequestDto ogrenciGuncellemeRequestDto);
}
