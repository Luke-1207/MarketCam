package com.marketcam.api.service;

import com.marketcam.api.dto.*;
import com.marketcam.api.mapper.PurchaseMapper;
import com.marketcam.api.mapper.ProductMapper;
import com.marketcam.api.model.Product;
import com.marketcam.api.model.Purchase;
import com.marketcam.api.model.PurchaseItem;
import com.marketcam.api.model.PurchaseItemId;
import com.marketcam.api.repository.ProductRepository;
import com.marketcam.api.repository.PurchaseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    @Transactional
    public List<PurchaseDTO> findAll() {
        List<Purchase> purchases = purchaseRepository.findAllWithItems();

        return purchases.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional
    public PurchaseDTO create(PurchaseCreateDTO dto) {
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(LocalDateTime.now());

        double total = 0.0;
        List<PurchaseItem> items = new ArrayList<>();

        for(PurchaseItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto com ID " + itemDTO.getProductId() + " não encontrado"
                    ));

            if(product.getQuantity() < itemDTO.getQuantity()) {
                throw new IllegalArgumentException(
                        "Estoque insuficiente para o produto: " + product.getName()
                );
            }

            product.setQuantity(product.getQuantity() - itemDTO.getQuantity());
            productRepository.save(product);

            PurchaseItem item = new PurchaseItem();
            item.setPurchase(purchase);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(product.getPrice());
            item.setId(new PurchaseItemId());

            items.add(item);

            total += product.getPrice() * item.getQuantity();
        }

        purchase.setItems(items);
        purchase.setTotalPrice(total);

        Purchase saved = purchaseRepository.save(purchase);
        return PurchaseMapper.convertToDTO(saved, PurchaseDTO.class);
    }

    @Transactional
    public void delete(Integer id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Compra não encontrada com ID: " + id));

        for (PurchaseItem item : purchase.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() + item.getQuantity());
            productRepository.save(product);
        }

        purchaseRepository.delete(purchase);
    }

    private PurchaseDTO convertToDTO(Purchase purchase) {
        List<PurchaseItemResponseDTO> itemDTOs = purchase.getItems() != null
                ? purchase.getItems().stream()
                    .map(item -> new PurchaseItemResponseDTO(
                            item.getProduct().getId(),
                            item.getProduct().getName(),
                            item.getUnitPrice(),
                            item.getQuantity()
                    ))
                    .toList()
                : new  ArrayList<>();

        return new PurchaseDTO(
                purchase.getId(),
                itemDTOs,
                purchase.getTotalPrice(),
                purchase.getPurchaseDate()
        );
    }
}
