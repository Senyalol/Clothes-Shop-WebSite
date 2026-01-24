package com.ClotheShop.CShop.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "locationshop")
public class Locationshop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locationshop_id_gen")
    @SequenceGenerator(name = "locationshop_id_gen", sequenceName = "locationshop_shopid_seq", allocationSize = 1)
    @Column(name = "shopid", nullable = false)
    private Integer id;

    @Column(name = "location", nullable = false)
    private String location;

    @ColumnDefault("0")
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "opening", nullable = false)
    private LocalTime opening;

    @Column(name = "closing", nullable = false)
    private LocalTime closing;

}