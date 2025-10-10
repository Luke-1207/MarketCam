package com.marketcam.api.service;

import com.marketcam.api.dto.PurchaseDTO;
import com.marketcam.api.dto.ProductDTO;
import com.marketcam.api.mapper.PurchaseMapper;
import com.marketcam.api.mapper.ProductMapper;
import com.marketcam.api.model.Product;
import com.marketcam.api.model.Purchase;
import com.marketcam.api.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public PurchaseDTO conclude(PurchaseDTO purchaseDTO) {
        Double totalValue = 0.0;
        List<Product> products = new ArrayList<>();
        for(ProductDTO product : purchaseDTO.getProducts()){
            totalValue += product.getPrice();
            products.add(ProductMapper.convertToEntity(product, Product.class));
        }

        Purchase purchase = new Purchase();
        purchase.setProducts(products);
        purchase.setTotalPrice(totalValue);
        purchase.setPurchaseDate(LocalDateTime.now());

        purchaseRepository.save(purchase);

        return PurchaseMapper.convertToDTO(purchase, PurchaseDTO.class);
    }

    public PurchaseDTO findById(Long idPurchase) {
        Purchase purchase = purchaseRepository.getReferenceById(idPurchase);
        return PurchaseMapper.convertToDTO(purchase, PurchaseDTO.class);
    }
}
