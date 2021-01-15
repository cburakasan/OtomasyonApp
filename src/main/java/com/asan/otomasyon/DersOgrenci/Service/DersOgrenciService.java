package com.asan.otomasyon.DersOgrenci.Service;

import com.asan.otomasyon.DersOgrenci.Dto.*;

public interface DersOgrenciService {
    OgrenciAlinanDersResponseDto ogrenciDersListeleme(OgrenciAlinanDersRequestDto ogrenciAlinanDersRequestDto);

    OgrenciDersSecmesiResponseDto ogrenciDersKaydi(OgrenciDersSecmesiRequestDto ogrenciDersSecmesiRequestDto);

    OgrenciDersSilResponseDto ogrenciDersSil(OgrenciDersSilRequestDto ogrenciDersSilRequestDto);

    DerseKayitliOgrenciResponseDto derseKayitliOgrenci(DerseKayitliOgrenciRequestDto derseKayitliOgrenciRequestDto);
}
