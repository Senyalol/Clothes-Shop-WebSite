package com.ClotheShop.CShop.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen", sequenceName = "users_user_id_seq", allocationSize = 1)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "secret_key", length = 100)
    private String secretKey;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

    @OneToMany(mappedBy = "user")
    private Set<Favorite> favorites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ShopCan> shopCans = new LinkedHashSet<>();
}