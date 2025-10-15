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
    private Integer id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "preco", nullable = false)
    private Double price;

    @Column(name = "categoria")
    private String category;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity = 0;

    @Lob
    @Column(name = "imagem", columnDefinition = "LONGTEXT")
    private byte[] imageData;

}
