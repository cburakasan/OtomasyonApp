package com.asan.otomasyon.Ogrenci.Service;

import com.asan.otomasyon.Ogrenci.Dto.*;
import com.asan.otomasyon.Ogrenci.Exception.OgrenciException;
import com.asan.otomasyon.Ogrenci.Model.Ogrenci;
import com.asan.otomasyon.Ogrenci.Repository.OgrenciRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class OgrenciServiceImpl implements OgrenciSercice {

    @Autowired
    OgrenciRepository ogrenciRepository;

    @Override
    public OgrenciListResponseDto getOgrenciList(OgrenciListRequestDto ogrenciListRequestDto) {
        OgrenciListResponseDto ogrenciListResponseDto = new OgrenciListResponseDto();

        try {
            List<Ogrenci> ogrenciList = ogrenciRepository.findAll();

            List<OgrenciDto> ogrenciDtoList = new ArrayList<>();

            for (Ogrenci ogrenci : ogrenciList) {
                OgrenciDto ogrenciDto = new OgrenciDto();

                Long ogrenciId = ogrenci.getOgrenciId();
                String isim = ogrenci.getIsim();
                String soyisim = ogrenci.getSoyisim();
                Long ogrenciNo = ogrenci.getOgrenciNo();

                ogrenciDto.setOgrenciId(ogrenciId);
                ogrenciDto.setIsim(isim);
                ogrenciDto.setSoyisim(soyisim);
                ogrenciDto.setOgrenciNo(ogrenciNo);

                ogrenciDtoList.add(ogrenciDto);

            }
            ogrenciListResponseDto.setOgrenciDtoList(ogrenciDtoList);
            log.info("Ogrenci Listeleme basarili");

            return ogrenciListResponseDto;
        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ogrenci listeleme sirasinda hata olustu");
            ogrenciListResponseDto.setMesaj(message + " " + "ogrenci listeleme sirasinda hata olustu");
            return ogrenciListResponseDto;

        }
    }

    @Override
    public OgrenciEkleResponseDto ogrenciEkle(OgrenciEkleRequestDto ogrenciEkleRequestDto) {

        OgrenciEkleResponseDto ogrenciEkleResponseDto = new OgrenciEkleResponseDto();
        try {
            Long ogrenciIdFromRequest = ogrenciEkleRequestDto.getOgrenciId();
            String isimFromRequest = ogrenciEkleRequestDto.getIsim();
            String soyisimFromRequest = ogrenciEkleRequestDto.getSoyisim();
            Long ogrenciNoFromRequest = ogrenciEkleRequestDto.getOgrenciNo();

            Ogrenci ogrenci = new Ogrenci();

            ogrenci.setOgrenciId(ogrenciIdFromRequest);
            ogrenci.setIsim(isimFromRequest);
            ogrenci.setSoyisim(soyisimFromRequest);
            ogrenci.setOgrenciNo(ogrenciNoFromRequest);

            ogrenci = ogrenciRepository.save(ogrenci);
            log.info("ogrenci kaydi basarili");
            ogrenciEkleResponseDto.setMesaj("ogrenci kaydi basarili");
            return ogrenciEkleResponseDto;
        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ogrenci kaydi olusturulamadi");
            ogrenciEkleResponseDto.setMesaj("ogrenci kaydi olusturulamadi");
            return ogrenciEkleResponseDto;
        }
    }

    @Override
    public OgrenciGuncellemeResponseDto ogrenciGuncelleme(OgrenciGuncellemeRequestDto ogrenciGuncellemeRequestDto) {

        OgrenciGuncellemeResponseDto ogrenciGuncellemeResponseDto = new OgrenciGuncellemeResponseDto();

        try {
            Long ogrenciIdFromRequest = ogrenciGuncellemeRequestDto.getOgrenciId();
            String isimFromRequest = ogrenciGuncellemeRequestDto.getIsim();
            String soyisimFromRequest = ogrenciGuncellemeRequestDto.getSoyisim();
            Long ogrenciNoFromRequest = ogrenciGuncellemeRequestDto.getOgrenciNo();

            Ogrenci ogrenci = ogrenciRepository.findByOgrenciId(ogrenciIdFromRequest);
            if (ogrenci.equals(null)) {
                throw new OgrenciException("Ogrenci bulunamadi", new Exception());

            }


            ogrenci.setOgrenciId(ogrenciIdFromRequest);
            ogrenci.setIsim(isimFromRequest);
            ogrenci.setSoyisim(soyisimFromRequest);
            ogrenci.setOgrenciNo(ogrenciNoFromRequest);

            ogrenciRepository.save(ogrenci);

            log.info("ogrenci guncelleme islemi basarili");
            ogrenciGuncellemeResponseDto.setMesaj("ogrenci guncelleme islemi basarili");
            return ogrenciGuncellemeResponseDto;


        } catch (OgrenciException ogrenciException) {
            String message = ogrenciException.getMessage();
            log.error(message + "ogrenci bulunamadi");
            ogrenciGuncellemeResponseDto.setMesaj("girilen id'ye ait ogrenci bulunamadi");
            return ogrenciGuncellemeResponseDto;

        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ogrenci guncelleme isleminde hata olustu");
            ogrenciGuncellemeResponseDto.setMesaj("ogrenci guncelleme isleminde hata olustu");
            return ogrenciGuncellemeResponseDto;
        }
    }


}
