package com.asan.otomasyon.Ders.Service;

import com.asan.otomasyon.Ders.Dto.*;

public interface DersService {
    DersListResponseDto getDersList(DersListRequestDto dersListRequestDto);

    DersEkleResponseDto dersEkle(DersEkleRequestDto dersEkleRequestDto);

    DersGuncellemeResponseDto dersGuncelleme(DersGuncellemeRequestDto dersGuncellemeRequestDto);
}
