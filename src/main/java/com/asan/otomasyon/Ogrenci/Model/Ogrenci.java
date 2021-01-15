package com.asan.otomasyon.Ogrenci.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class Ogrenci implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long ogrenciId;

    @Column
    private String isim;

    @Column
    private String soyisim;

    @Column
    private Long ogrenciNo;


}
