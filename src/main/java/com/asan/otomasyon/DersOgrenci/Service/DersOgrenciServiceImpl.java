package com.asan.otomasyon.DersOgrenci.Service;

import com.asan.otomasyon.Ders.Dto.DersDto;
import com.asan.otomasyon.Ders.Exception.DersException;
import com.asan.otomasyon.Ders.Model.Ders;
import com.asan.otomasyon.Ders.Repository.DersRepositroy;
import com.asan.otomasyon.DersOgrenci.Dto.*;
import com.asan.otomasyon.DersOgrenci.Exception.DersOgrenciException;
import com.asan.otomasyon.DersOgrenci.Model.DersOgrenci;
import com.asan.otomasyon.DersOgrenci.Repository.DersOgrenciRepository;
import com.asan.otomasyon.Ogrenci.Dto.OgrenciDto;
import com.asan.otomasyon.Ogrenci.Exception.OgrenciException;
import com.asan.otomasyon.Ogrenci.Model.Ogrenci;
import com.asan.otomasyon.Ogrenci.Repository.OgrenciRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Log4j
public class DersOgrenciServiceImpl implements DersOgrenciService {

    @Autowired
    DersOgrenciRepository dersOgrenciRepository;

    @Autowired
    OgrenciRepository ogrenciRepository;

    @Autowired
    DersRepositroy dersRepositroy;


    @Override
    public OgrenciAlinanDersResponseDto ogrenciDersListeleme(OgrenciAlinanDersRequestDto ogrenciAlinanDersRequestDto) {

        OgrenciAlinanDersResponseDto ogrenciAlinanDersResponseDto = new OgrenciAlinanDersResponseDto();

        try {
            Long ogrenciIdFromRequest = ogrenciAlinanDersRequestDto.getOgrenciId();

            Ogrenci ogrenci = ogrenciRepository.findByOgrenciId(ogrenciIdFromRequest);
            if (ogrenci.equals(null)) {
                throw new OgrenciException("Ogrenci bulunamadi", new Exception());
            }

            List<DersOgrenci> dersOgrenciList = dersOgrenciRepository.findAllByOgrenci(ogrenci);
            Integer sizeList = dersOgrenciList.size();
            if (sizeList == null) {
                throw new DersOgrenciException("Ogrencinin secmis oldugu ders bulunamadi", new Exception());
            }

            List<DersDto> dersDtoList = new ArrayList<>();
            for (DersOgrenci dersOgrenci : dersOgrenciList) {
                DersDto dersDto = new DersDto();
                Ders ders = dersOgrenci.getDers();
                Long dersId = ders.getDersId();
                String dersAdi = ders.getDersAdi();
                dersDto.setDersId(dersId);
                dersDto.setDersinAdi(dersAdi);
                dersDtoList.add(dersDto);
            }
            ogrenciAlinanDersResponseDto.setMesaj(ogrenciIdFromRequest + " " + "id'li ogrencinin secmis oldugu dersler");
            ogrenciAlinanDersResponseDto.setDersDtoList(dersDtoList);
            log.info("ogrencinin secmis oldugu ders listeleme basarili");
            return ogrenciAlinanDersResponseDto;

        } catch (OgrenciException ogrenciException) {

            String message = ogrenciException.getMessage();
            log.error(message);
            ogrenciAlinanDersResponseDto.setMesaj(message);
            return ogrenciAlinanDersResponseDto;
        } catch (DersOgrenciException dersOgrenciException) {
            String message = dersOgrenciException.getMessage();
            log.error(message);
            ogrenciAlinanDersResponseDto.setMesaj(message);
            return ogrenciAlinanDersResponseDto;
        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ogrencinin ders listelemesi sirasinda bir hata olustu");
            ogrenciAlinanDersResponseDto.setMesaj("ogrencinin ders listelemesi sirasinda bir hata olustu");
            return ogrenciAlinanDersResponseDto;
        }
    }

    @Override
    public OgrenciDersSecmesiResponseDto ogrenciDersKaydi(OgrenciDersSecmesiRequestDto ogrenciDersSecmesiRequestDto) {

        OgrenciDersSecmesiResponseDto ogrenciDersSecmesiResponseDto = new OgrenciDersSecmesiResponseDto();
        DersDto dersDto = new DersDto();
        try {
            Long dersIdFromRequest = ogrenciDersSecmesiRequestDto.getDersId();
            Long ogrenciIdFromRequest = ogrenciDersSecmesiRequestDto.getOgrenciId();

            Ogrenci ogrenci = ogrenciRepository.findByOgrenciId(ogrenciIdFromRequest);
            if (ogrenci.equals(null)) {
                throw new OgrenciException("Ogrenci bulunamadi", new Exception());
            }

            Ders ders = dersRepositroy.findByDersId(dersIdFromRequest);
            if (ders.equals(null)) {
                throw new DersException("Ders bulunamadi", new Exception());
            }
            Long dersId = ders.getDersId();
            String dersAdi = ders.getDersAdi();
            Long kontenjan = ders.getKontenjan();

            List<DersOgrenci> dersOgrenciList = dersOgrenciRepository.findAllByDers(ders);
            Integer size = dersOgrenciList.size();
            if (size.equals(null)) {
                throw new DersOgrenciException("Ders ogrenci listesi bulunamadi", new Exception());
            }
            List<Ogrenci> ogrenciList = new ArrayList<>();
            for (DersOgrenci dersOgrenci : dersOgrenciList) {
                Ogrenci ogrenciFromDers = dersOgrenci.getOgrenci();
                ogrenciList.add(ogrenciFromDers);
            }
            Integer derseKayitliOgrenciSayisi = ogrenciList.size();
            if (derseKayitliOgrenciSayisi >= kontenjan) {
                throw new DersOgrenciException("Dersin kontenjani dolu", new Exception());
            }


            dersDto.setDersId(dersId);
            dersDto.setDersinAdi(dersAdi);


            DersOgrenci dersOgrenci = new DersOgrenci();
            dersOgrenci.setOgrenci(ogrenci);
            dersOgrenci.setDers(ders);

            dersOgrenci = dersOgrenciRepository.save(dersOgrenci);
            log.info("ders kaydi basarili");
            ogrenciDersSecmesiResponseDto.setDersDto(dersDto);
            ogrenciDersSecmesiResponseDto.setMesaj("ders secimi basarili");
            return ogrenciDersSecmesiResponseDto;


        } catch (OgrenciException ogrenciException) {
            String message = ogrenciException.getMessage();
            log.error(message);
            ogrenciDersSecmesiResponseDto.setMesaj("ogrenci id'si hatali");
            return ogrenciDersSecmesiResponseDto;

        } catch (DersException dersException) {
            String message = dersException.getMessage();
            log.error(message);
            ogrenciDersSecmesiResponseDto.setMesaj("Ders id'si hatali");
            return ogrenciDersSecmesiResponseDto;

        } catch (DersOgrenciException dersOgrenciException) {
            String message = dersOgrenciException.getMessage();
            log.error(message);
            ogrenciDersSecmesiResponseDto.setMesaj(message);
            return ogrenciDersSecmesiResponseDto;


        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ders secimi sirasinda bir hata olustu");
            ogrenciDersSecmesiResponseDto.setMesaj("ders secimi sirasinda bir hata olustu");
            return ogrenciDersSecmesiResponseDto;
        }
    }

    @Override
    public OgrenciDersSilResponseDto ogrenciDersSil(OgrenciDersSilRequestDto ogrenciDersSilRequestDto) {

        OgrenciDersSilResponseDto ogrenciDersSilResponseDto = new OgrenciDersSilResponseDto();

        try {
            Long ogrenciIdFromRequest = ogrenciDersSilRequestDto.getOgrenciId();
            Long dersIdFromRequest = ogrenciDersSilRequestDto.getDersId();

            Ogrenci ogrenci = ogrenciRepository.findByOgrenciId(ogrenciIdFromRequest);
            if (ogrenci.equals(null)) {
                throw new OgrenciException("Ogrenci bulunamadi", new Exception());
            }

            Ders ders = dersRepositroy.findByDersId(dersIdFromRequest);
            if (ders.equals(null)) {
                throw new DersException("Ders bulunamadi", new Exception());
            }

            DersOgrenci dersOgrenci = dersOgrenciRepository.findByOgrenciAndDers(ogrenci, ders);

            if (dersOgrenci.equals(null)) {
                throw new DersOgrenciException("Ogrencinin silmek istedigi ders bulunamadi", new Exception());
            }

            Ders birakilacakDers = dersOgrenci.getDers();
            dersOgrenciRepository.deleteDersOgrenciByDersAndOgrenci(birakilacakDers, ogrenci);
            log.info("Ders silindi");
            ogrenciDersSilResponseDto.setMesaj("Ders silindi");
            return ogrenciDersSilResponseDto;


        } catch (OgrenciException ogrenciException) {
            String message = ogrenciException.getMessage();
            log.error(message);
            ogrenciDersSilResponseDto.setMesaj(message);
            return ogrenciDersSilResponseDto;


        } catch (DersException dersException) {
            String message = dersException.getMessage();
            log.error(message);
            ogrenciDersSilResponseDto.setMesaj(message);
            return ogrenciDersSilResponseDto;
        } catch (DersOgrenciException dersOgrenciException) {
            String message = dersOgrenciException.getMessage();
            log.error(message);
            ogrenciDersSilResponseDto.setMesaj(message);
            return ogrenciDersSilResponseDto;
        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "ders silme islemi sirasinda bir hata olustu");
            ogrenciDersSilResponseDto.setMesaj("ders silme islemi sirasinda bir hata olustu");
            return ogrenciDersSilResponseDto;
        }

    }

    @Override
    public DerseKayitliOgrenciResponseDto derseKayitliOgrenci(DerseKayitliOgrenciRequestDto derseKayitliOgrenciRequestDto) {

        DerseKayitliOgrenciResponseDto derseKayitliOgrenciResponseDto = new DerseKayitliOgrenciResponseDto();
        try {
            Long dersIdFromRequest = derseKayitliOgrenciRequestDto.getDersId();
            Ders ders = dersRepositroy.findByDersId(dersIdFromRequest);
            if (ders.equals(null)) {
                throw new DersException("Ders bulunamadi", new Exception());
            }

            List<DersOgrenci> dersOgrenciList = dersOgrenciRepository.findAllByDers(ders);
            Integer size = dersOgrenciList.size();
            if (size.equals(null)) {
                throw new DersOgrenciException("Ders ogrenci listesi bulunamadi", new Exception());
            }
            List<OgrenciDto> ogrenciDtoList = new ArrayList<>();
            for (DersOgrenci dersOgrenci : dersOgrenciList) {
                OgrenciDto ogrenciDto = new OgrenciDto();

                Ogrenci ogrenci = dersOgrenci.getOgrenci();
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
            derseKayitliOgrenciResponseDto.setMesaj(dersIdFromRequest + " " + "id'li derse kayitli ogrenci listesi");
            derseKayitliOgrenciResponseDto.setOgrenciDtoList(ogrenciDtoList);
            return derseKayitliOgrenciResponseDto;

        } catch (DersException dersException) {
            String message = dersException.getMessage();
            log.error(message);
            derseKayitliOgrenciResponseDto.setMesaj(message);
            return derseKayitliOgrenciResponseDto;

        } catch (DersOgrenciException dersOgrenciException) {
            String message = dersOgrenciException.getMessage();
            log.error(message);
            derseKayitliOgrenciResponseDto.setMesaj(message);
            return derseKayitliOgrenciResponseDto;
        } catch (Exception exception) {
            String message = exception.getMessage();
            log.error(message + "derse kayitli ogrenciler listelenirken bir hata olustu");
            derseKayitliOgrenciResponseDto.setMesaj("derse kayitli ogrenciler listelenirken bir hata olustu");
            return derseKayitliOgrenciResponseDto;
        }

    }
}
