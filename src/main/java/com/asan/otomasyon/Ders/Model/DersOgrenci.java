package com.asan.otomasyon.Ders.Model;

import com.asan.otomasyon.Ogrenci.Model.Ogrenci;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class DersOgrenci {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long dersOgrenciId;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ogrenci",foreignKey = @ForeignKey(name = "FK_Ogrenci_DersOgrenci"))
    private Ogrenci ogrenci;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ders",foreignKey = @ForeignKey(name = "FK_Ders_DersOgrenci"))
    private Ders ders;


}
