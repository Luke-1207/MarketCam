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
@Table(name = "compra_produto")
public class PurchaseItem {

    @EmbeddedId
    private PurchaseItemId id = new PurchaseItemId();

    @ManyToOne
    @MapsId("purchaseId")
    @JoinColumn(name = "id_compra")
    private Purchase purchase;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "id_produto")
    private Product product;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @Column(name = "preco_unitario", nullable = false)
    private Double unitPrice;
}
