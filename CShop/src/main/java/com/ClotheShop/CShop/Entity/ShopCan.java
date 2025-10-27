package com.ClotheShop.CShop.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "shop_can")
public class ShopCan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_can_id_gen")
    @SequenceGenerator(name = "shop_can_id_gen", sequenceName = "shop_can_shop_can_id_seq", allocationSize = 1)
    @Column(name = "shop_can_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "cost")
    private Double cost;

    @ColumnDefault("false")
    @Column(name = "paid", nullable = false)
    private Boolean paid = false;

}