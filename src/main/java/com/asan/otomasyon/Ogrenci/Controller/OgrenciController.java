package com.asan.otomasyon.Ogrenci.Controller;


import com.asan.otomasyon.Ogrenci.Dto.*;
import com.asan.otomasyon.Ogrenci.Service.OgrenciSercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ogrenci")
public class OgrenciController {

    @Autowired
    OgrenciSercice ogrenciSercice;


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



}
