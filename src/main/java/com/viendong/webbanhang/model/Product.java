package com.viendong.webbanhang.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column (nullable = false)
    private String image;

    @Column(nullable = false)
    private double price;


    // Mô tả sản phẩm
    private String description;

    // Liên kết với bảng category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
