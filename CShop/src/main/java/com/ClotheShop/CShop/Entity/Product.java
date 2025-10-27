package com.ClotheShop.CShop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_gen")
    @SequenceGenerator(name = "product_id_gen", sequenceName = "product_product_id_seq", allocationSize = 1)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "availability", nullable = false)
    private Boolean availability = false;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private Integer size;

    @Column(name = "sex", length = 200)
    private String sex;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "product")
    private Set<Favorite> favorites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> productImages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ShopCan> shopCans = new LinkedHashSet<>();

}