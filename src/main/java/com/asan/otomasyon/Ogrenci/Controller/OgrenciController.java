package com.asan.otomasyon.Ogrenci.Controller;


import com.asan.otomasyon.DersOgrenci.Dto.*;
import com.asan.otomasyon.DersOgrenci.Service.DersOgrenciService;
import com.asan.otomasyon.Ogrenci.Dto.*;
import com.asan.otomasyon.Ogrenci.Service.OgrenciSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ogrenci")
public class OgrenciController {

    @Autowired
    OgrenciSercice ogrenciSercice;

    @Autowired
    DersOgrenciService dersOgrenciService;


    @PostMapping("/list")
    public OgrenciListResponseDto getOgrenciList(@RequestBody OgrenciListRequestDto ogrenciListRequestDto) {
        return ogrenciSercice.getOgrenciList(ogrenciListRequestDto);

    }

    @PostMapping("/ekle")
    public OgrenciEkleResponseDto ogrenciEkle(@RequestBody OgrenciEkleRequestDto ogrenciEkleRequestDto) {

        return ogrenciSercice.ogrenciEkle(ogrenciEkleRequestDto);

    }

    @PostMapping("/guncelleme")
    public OgrenciGuncellemeResponseDto ogrenciGuncelleme (@RequestBody OgrenciGuncellemeRequestDto ogrenciGuncellemeRequestDto){

        return ogrenciSercice.ogrenciGuncelleme(ogrenciGuncellemeRequestDto);
    }

    @PostMapping("/derssecim")
    public OgrenciDersSecmesiResponseDto dersKaydi(@RequestBody OgrenciDersSecmesiRequestDto ogrenciDersSecmesiRequestDto){

        return dersOgrenciService.ogrenciDersKaydi(ogrenciDersSecmesiRequestDto);
    }

    @PostMapping("/dersList")
    public OgrenciAlinanDersResponseDto ogrenciDersListeleme (@RequestBody OgrenciAlinanDersRequestDto ogrenciAlinanDersRequestDto){

        return dersOgrenciService.ogrenciDersListeleme(ogrenciAlinanDersRequestDto);
    }

    @PostMapping("/dersSil")
    public OgrenciDersSilResponseDto ogrenciDersSil (@RequestBody OgrenciDersSilRequestDto ogrenciDersSilRequestDto){

        return  dersOgrenciService.ogrenciDersSil(ogrenciDersSilRequestDto);
    }





}
