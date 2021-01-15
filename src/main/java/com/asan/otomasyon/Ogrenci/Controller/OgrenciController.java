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


    @PostMapping("/list") //Sistemde tanımlı öğrencilerin listelenmesini saglayan servis.
    public OgrenciListResponseDto getOgrenciList(@RequestBody OgrenciListRequestDto ogrenciListRequestDto) {

        return ogrenciSercice.getOgrenciList(ogrenciListRequestDto);

    }

    @PostMapping("/ekle") //Yeni bir öğrenci tanımlanmasını saglayan servis.
    public OgrenciEkleResponseDto ogrenciEkle(@RequestBody OgrenciEkleRequestDto ogrenciEkleRequestDto) {

        return ogrenciSercice.ogrenciEkle(ogrenciEkleRequestDto);

    }

    @PostMapping("/guncelleme") //Sistemde mevcut olan ogrenciyi guncelleyen servis.
    public OgrenciGuncellemeResponseDto ogrenciGuncelleme(@RequestBody OgrenciGuncellemeRequestDto ogrenciGuncellemeRequestDto) {

        return ogrenciSercice.ogrenciGuncelleme(ogrenciGuncellemeRequestDto);
    }

    @PostMapping("/derssecim") /* Bir ogrencinin derse kaydolmasini saglayan servis.Derse kayit olurken
    daha onceden tanimlanmis olan kontenjan kontrolu yapilir kontenjan dolu ise hata mesaji yayinlanir.*/
    public OgrenciDersSecmesiResponseDto dersKaydi(@RequestBody OgrenciDersSecmesiRequestDto ogrenciDersSecmesiRequestDto) {

        return dersOgrenciService.ogrenciDersKaydi(ogrenciDersSecmesiRequestDto);
    }

    @PostMapping("/dersList") // Bir öğrencinin aldığı derslerin listelenmesini saglayan servis.
    public OgrenciAlinanDersResponseDto ogrenciDersListeleme(@RequestBody OgrenciAlinanDersRequestDto ogrenciAlinanDersRequestDto) {

        return dersOgrenciService.ogrenciDersListeleme(ogrenciAlinanDersRequestDto);
    }

    @PostMapping("/dersSil") // Bir ogrencinin bir dersi birakmasini saglayan servis.
    public OgrenciDersSilResponseDto ogrenciDersSil(@RequestBody OgrenciDersSilRequestDto ogrenciDersSilRequestDto) {

        return dersOgrenciService.ogrenciDersSil(ogrenciDersSilRequestDto);
    }


}
