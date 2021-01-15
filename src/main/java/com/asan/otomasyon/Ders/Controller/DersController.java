package com.asan.otomasyon.Ders.Controller;


import com.asan.otomasyon.Ders.Dto.*;
import com.asan.otomasyon.Ders.Service.DersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ders")
public class DersController {

    @Autowired
    DersService dersSercice;

    @PostMapping("/list")
    public DersListResponseDto getDersList(@RequestBody DersListRequestDto dersListRequestDto) {
        return dersSercice.getDersList(dersListRequestDto);
    }

    @PostMapping("/ekle")
    public DersEkleResponseDto dersEkle(@RequestBody DersEkleRequestDto dersEkleRequestDto) {
        return dersSercice.dersEkle(dersEkleRequestDto);
    }

    @PostMapping("/guncelleme")
    public DersGuncellemeResponseDto dersGuncelleme(@RequestBody DersGuncellemeRequestDto dersGuncellemeRequestDto){
        return dersSercice.dersGuncelleme(dersGuncellemeRequestDto);
    }



}
