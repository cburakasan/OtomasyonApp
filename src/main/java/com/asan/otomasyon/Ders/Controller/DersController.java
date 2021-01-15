package com.asan.otomasyon.Ders.Controller;


import com.asan.otomasyon.Ders.Dto.*;
import com.asan.otomasyon.Ders.Service.DersService;
import com.asan.otomasyon.DersOgrenci.Dto.DerseKayitliOgrenciRequestDto;
import com.asan.otomasyon.DersOgrenci.Dto.DerseKayitliOgrenciResponseDto;
import com.asan.otomasyon.DersOgrenci.Service.DersOgrenciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ders")
public class DersController {

    @Autowired
    DersService dersService;

    @Autowired
    DersOgrenciService dersOgrenciService;

    @PostMapping("/list")
    public DersListResponseDto getDersList(@RequestBody DersListRequestDto dersListRequestDto) {
        return dersService.getDersList(dersListRequestDto);
    }

    @PostMapping("/ekle")
    public DersEkleResponseDto dersEkle(@RequestBody DersEkleRequestDto dersEkleRequestDto) {
        return dersService.dersEkle(dersEkleRequestDto);
    }

    @PostMapping("/guncelleme")
    public DersGuncellemeResponseDto dersGuncelleme(@RequestBody DersGuncellemeRequestDto dersGuncellemeRequestDto) {
        return dersService.dersGuncelleme(dersGuncellemeRequestDto);
    }

    @PostMapping("/ogrencilist")
    public DerseKayitliOgrenciResponseDto derseKayitliOgrenci(@RequestBody DerseKayitliOgrenciRequestDto derseKayitliOgrenciRequestDto) {
        return dersOgrenciService.derseKayitliOgrenci(derseKayitliOgrenciRequestDto);
    }


}
