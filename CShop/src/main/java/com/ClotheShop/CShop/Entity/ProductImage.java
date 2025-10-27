package com.ClotheShop.CShop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_image_id_gen")
    @SequenceGenerator(name = "product_image_id_gen", sequenceName = "product_image_product_image_id_seq", allocationSize = 1)
    @Column(name = "product_image_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "image", nullable = false, length = 1500)
    private String image;

}