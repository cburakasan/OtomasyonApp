package com.asan.otomasyon.Ogrenci.Service;

import com.asan.otomasyon.Ogrenci.Model.Ogrenci;
import com.asan.otomasyon.Ogrenci.Repository.OgrenciRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OgrenciServiceImplTest {

    @Autowired
    OgrenciRepository ogrenciRepository;

    @Test
    @Rollback(value = false)
    public void testOgrenciEkle(){
        Ogrenci ogrenci = new Ogrenci();
        ogrenci.setIsim("Burak");
        ogrenci.setSoyisim("asan");
        ogrenci.setOgrenciNo(5l);
        ogrenci=ogrenciRepository.save(ogrenci);
        assertNotNull(ogrenci);
    }

    @Test
    @Rollback(value = false)
    public void testOgrenciList(){
        List<Ogrenci> ogrenciList = ogrenciRepository.findAll();
        assertNotNull(ogrenciList);

    }

    @Test
    @Rollback(value = false)
    public void testOgrenciGuncelleme(){
        testOgrenciEkle();
        Ogrenci ogrenci = ogrenciRepository.findByOgrenciId(1l);
        ogrenci.setIsim("Ahmet");
        ogrenci.setSoyisim("demir");
        ogrenci.setOgrenciNo(7l);
        ogrenci=ogrenciRepository.save(ogrenci);
        assertNotNull(ogrenci);
    }





}