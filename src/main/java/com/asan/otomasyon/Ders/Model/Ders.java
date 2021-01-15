package com.asan.otomasyon.Ders.Model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
public class Ders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long dersId;

    @Column
    private String dersAdi;

    @Column
    private Long kontenjan;


}
