package com.asan.otomasyon.Ogrenci.Repository;

import com.asan.otomasyon.Ogrenci.Model.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    Ogrenci findByOgrenciId(Long ogrenciId);

}
