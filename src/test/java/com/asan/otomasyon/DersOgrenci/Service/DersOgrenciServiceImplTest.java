package com.asan.otomasyon.DersOgrenci.Service;

import com.asan.otomasyon.Ders.Model.Ders;
import com.asan.otomasyon.Ders.Repository.DersRepositroy;
import com.asan.otomasyon.DersOgrenci.Model.DersOgrenci;
import com.asan.otomasyon.DersOgrenci.Repository.DersOgrenciRepository;
import com.asan.otomasyon.Ogrenci.Model.Ogrenci;
import com.asan.otomasyon.Ogrenci.Repository.OgrenciRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DersOgrenciServiceImplTest {

    @Autowired
    DersOgrenciRepository dersOgrenciRepository;

    @Autowired
    OgrenciRepository ogrenciRepository;

    @Autowired
    DersRepositroy dersRepositroy;


    @Test
    @Rollback(value = false)
    public void testOgrenciDersKaydi() {

        Ders ders = new Ders();
        ders.setDersAdi("Fizik");
        ders.setKontenjan(5l);
        ders = dersRepositroy.save(ders);
        assertNotNull(ders);

        Ogrenci ogrenci = new Ogrenci();
        ogrenci.setIsim("Burak");
        ogrenci.setSoyisim("asan");
        ogrenci.setOgrenciNo(5l);
        ogrenci = ogrenciRepository.save(ogrenci);
        assertNotNull(ogrenci);

        Ogrenci ogrenciFromDb = ogrenciRepository.findByOgrenciId(1l);
        Ders dersFromDb = dersRepositroy.findByDersId(1l);

        DersOgrenci dersOgrenci = new DersOgrenci();
        dersOgrenci.setOgrenci(ogrenciFromDb);
        dersOgrenci.setDers(dersFromDb);

        dersOgrenciRepository.save(dersOgrenci);
        assertNotNull(dersOgrenci);


    }

    @Test
    @Rollback(value = false)
    public void testOgrenciDersListeleme() {
        Ogrenci ogrenci = new Ogrenci();
        ogrenci.setIsim("Burak");
        ogrenci.setSoyisim("asan");
        ogrenci.setOgrenciNo(5l);
        ogrenci = ogrenciRepository.save(ogrenci);
        assertNotNull(ogrenci);

        Ogrenci ogrenciFromDb = ogrenciRepository.findByOgrenciId(1l);
        assertNotNull(ogrenciFromDb);

        List<DersOgrenci> dersOgrenciList = dersOgrenciRepository.findAllByOgrenci(ogrenciFromDb);

        List<Ders> dersList = new ArrayList<>();

        for (DersOgrenci dersOgrenci : dersOgrenciList) {
            Ders ders = dersOgrenci.getDers();
            dersList.add(ders);
        }
        assertNotNull(dersList);
    }


    @Test
    @Rollback(value = false)
    public void testDerseKayitliOgrenciListeleme() {

        Ders ders = new Ders();
        ders.setDersAdi("Fizik");
        ders.setKontenjan(5l);
        ders = dersRepositroy.save(ders);
        assertNotNull(ders);

        Ders dersFromDb = dersRepositroy.findByDersId(1l);
        assertNotNull(dersFromDb);

        List<DersOgrenci> dersOgrenciList = dersOgrenciRepository.findAllByDers(dersFromDb);
        assertNotNull(dersOgrenciList);

        List<Ogrenci> ogrenciList = new ArrayList<>();

        for (DersOgrenci dersOgrenci : dersOgrenciList) {
            Ogrenci ogrenci = dersOgrenci.getOgrenci();
            ogrenciList.add(ogrenci);
        }
        assertNotNull(ogrenciList);

    }

    @Test
    @Rollback(value = false)
    public void testOgrenciDersSil() {

        Ders ders = new Ders();
        ders.setDersAdi("Fizik");
        ders.setKontenjan(5l);
        ders = dersRepositroy.save(ders);
        assertNotNull(ders);

        Ogrenci ogrenci = new Ogrenci();
        ogrenci.setIsim("Burak");
        ogrenci.setSoyisim("asan");
        ogrenci.setOgrenciNo(5l);
        ogrenci = ogrenciRepository.save(ogrenci);
        assertNotNull(ogrenci);

        Ders dersFromDb = dersRepositroy.findByDersId(1l);
        assertNotNull(dersFromDb);

        ogrenci = ogrenciRepository.save(ogrenci);
        assertNotNull(ogrenci);

        DersOgrenci dersOgrenci = dersOgrenciRepository.deleteByDersWhereOgrenci(ders, ogrenci);
        assertNull(dersOgrenci);


    }


}