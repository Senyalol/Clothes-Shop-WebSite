package com.ClotheShop.CShop.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "usershop")
public class UserShop {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usershop_id_gen")
    @SequenceGenerator(name = "usershop_id_gen", sequenceName = "usershop_user_shop_id_seq", allocationSize = 1)
    @Column(name = "user_shop_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "shop_id", nullable = false)
    private Locationshop shop;

}