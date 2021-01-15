package com.asan.otomasyon.Ders.Service;

import com.asan.otomasyon.Ders.Dto.*;
import com.asan.otomasyon.Ders.Exception.DersException;
import com.asan.otomasyon.Ders.Model.Ders;
import com.asan.otomasyon.Ders.Repository.DersRepositroy;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class DersServiceImpl implements DersService {

    @Autowired
    DersRepositroy dersRepositroy;

    @Override
    public DersListResponseDto getDersList(DersListRequestDto dersListRequestDto) {

        DersListResponseDto dersListResponseDto = new DersListResponseDto();

        try {
            List<Ders> dersList = dersRepositroy.findAll();

            List<DersDto> dersDtoList = new ArrayList<>();

            for (Ders ders : dersList) {
                DersDto dersDto = new DersDto();

                Long dersId = ders.getDersId();
                String dersinAdi = ders.getDersAdi();
                Long kontenjan = ders.getKontenjan();

                dersDto.setDersId(dersId);
                dersDto.setDersinAdi(dersinAdi);
                dersDto.setKontenjan(kontenjan);

                dersDtoList.add(dersDto);

            }
            dersListResponseDto.setMesaj("Ders Listesi:");
            dersListResponseDto.setDersDtoList(dersDtoList);
            log.info("Ders Listeleme basarili");

            return dersListResponseDto;
        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ders listeleme sirasinda hata olustu");
            dersListResponseDto.setMesaj("ders listeleme sirasinda hata olustu");
            return dersListResponseDto;
        }
    }

    @Override
    public DersEkleResponseDto dersEkle(DersEkleRequestDto dersEkleRequestDto) {

        DersEkleResponseDto dersEkleResponseDto = new DersEkleResponseDto();
        try {
            String dersAdiFromRequest = dersEkleRequestDto.getDersAdi();
            Long kontenjanFromRequest = dersEkleRequestDto.getKontenjan();

            Ders ders = new Ders();

            ders.setDersAdi(dersAdiFromRequest);
            ders.setKontenjan(kontenjanFromRequest);
            ders = dersRepositroy.save(ders);
            log.info("ders kaydi basarili");
            dersEkleResponseDto.setMesaj("ders kaydi basarili");
            return dersEkleResponseDto;
        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ders kaydi olusturulamadi");
            dersEkleResponseDto.setMesaj("ders kaydi olusturulamadi");
            return dersEkleResponseDto;
        }
    }

    @Override
    public DersGuncellemeResponseDto dersGuncelleme(DersGuncellemeRequestDto dersGuncellemeRequestDto) {

        DersGuncellemeResponseDto dersGuncellemeResponseDto = new DersGuncellemeResponseDto();
        try {
            Long dersIdFromRequest = dersGuncellemeRequestDto.getDersId();
            String dersAdiFromRequest = dersGuncellemeRequestDto.getDersAdi();
            Long kontenjanFromRequest = dersGuncellemeRequestDto.getKontenjan();

            Ders ders = dersRepositroy.findByDersId(dersIdFromRequest);
            if (ders.equals(null)) {
                throw new DersException("Ders bulunamadi", new Exception());
            }
            ders.setDersId(dersIdFromRequest);
            ders.setDersAdi(dersAdiFromRequest);
            ders.setKontenjan(kontenjanFromRequest);

            ders = dersRepositroy.save(ders);

            log.info("ders guncelleme islemi basarili");
            dersGuncellemeResponseDto.setMesaj("ders guncelleme islemi basarili");
            return dersGuncellemeResponseDto;

        } catch (DersException dersException) {
            String message = dersException.getMessage();
            log.error(message);
            dersGuncellemeResponseDto.setMesaj("girilen id'ye ait ders bulunamadi");
            return dersGuncellemeResponseDto;

        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ders guncelleme isleminde hata olustu");
            dersGuncellemeResponseDto.setMesaj("ders guncelleme isleminde hata olustu");
            return dersGuncellemeResponseDto;
        }
    }
}
