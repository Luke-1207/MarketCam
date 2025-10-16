package com.marketcam.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class PurchaseItemId implements Serializable {

    @Column(name = "id_compra")
    private Integer purchaseId;

    @Column(name = "id_produto")
    private Integer productId;

}
