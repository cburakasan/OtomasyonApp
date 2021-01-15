package com.asan.otomasyon.DersOgrenci.Repository;

import com.asan.otomasyon.Ders.Model.Ders;
import com.asan.otomasyon.DersOgrenci.Model.DersOgrenci;
import com.asan.otomasyon.Ogrenci.Model.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DersOgrenciRepository extends JpaRepository<DersOgrenci,Long> {

    List<DersOgrenci> findAllByOgrenci(Ogrenci ogrenci);

    DersOgrenci findByOgrenciAndDers (Ogrenci ogrenci,Ders ders );

    DersOgrenci deleteByDers(Ders ders);

    List<DersOgrenci> findAllByDers(Ders ders);

}
