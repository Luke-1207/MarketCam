package com.marketcam.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @Column(name = "preco", nullable = false)
    private Double price;

    @Column(name = "categoria", nullable = false)
    private String category;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity = 0;

    @Lob
    @Column(name = "imagem", columnDefinition = "LONGTEXT")
    private String imageData;

}
