package com.asan.otomasyon.Ders.Service;

import com.asan.otomasyon.Ders.Model.Ders;
import com.asan.otomasyon.Ders.Repository.DersRepositroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DersServiceImplTest {

    @Autowired
    DersRepositroy dersRepositroy;

    @Test
    @Rollback(value = false)
    public void testDersEkle() {
        Ders ders = new Ders();
        ders.setDersAdi("Fizik");
        ders.setKontenjan(5l);
        ders = dersRepositroy.save(ders);
        assertNotNull(ders);
    }

    @Test
    @Rollback(value = false)
    public void testDersList() {
        List<Ders> dersList = dersRepositroy.findAll();
        assertNotNull(dersList);

    }

    @Test
    @Rollback(value = false)
    public void testDersGuncelleme() {
        testDersEkle();
        Ders ders = dersRepositroy.findByDersId(1l);
        ders.setDersAdi("Kimya");
        ders.setKontenjan(15l);
        ders = dersRepositroy.save(ders);
        assertNotNull(ders);

    }

}