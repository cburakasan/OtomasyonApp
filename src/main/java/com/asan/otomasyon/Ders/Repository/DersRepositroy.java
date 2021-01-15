package com.asan.otomasyon.Ders.Repository;

import com.asan.otomasyon.Ders.Model.Ders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DersRepositroy extends JpaRepository<Ders,Long> {

    Ders findByDersId(Long dersId);


}
