package com.marketcam.api.service;

import com.marketcam.api.dto.ProductCreateDTO;
import com.marketcam.api.dto.ProductDTO;
import com.marketcam.api.dto.ProductUpdateDTO;
import com.marketcam.api.mapper.ProductCreateMapper;
import com.marketcam.api.mapper.ProductMapper;
import com.marketcam.api.model.Product;
import com.marketcam.api.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO create(ProductCreateDTO productCreateDTO, MultipartFile image) {
        Product product = ProductCreateMapper.convertToEntity(productCreateDTO, Product.class);
        if (image != null && !image.isEmpty()) {
            product.setImageData(convertImageBytesToString(image));
        }
        product = productRepository.save(product);

        return ProductMapper.convertToEntity(product, ProductDTO.class);
    }

    public ProductDTO findById(Long idProduct) {
        Product product = productRepository.getReferenceById(idProduct);
        return ProductMapper.convertToDTO(product, ProductDTO.class);
    }

    public List<ProductDTO> findAll(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product : products){
            productDTOs.add(ProductMapper.convertToDTO(product, ProductDTO.class));
        }

        return productDTOs;
    }

    public ProductDTO update(ProductUpdateDTO productUpdateDTO, MultipartFile image){
        productRepository.findById(productUpdateDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + productUpdateDTO.getId() + " não encontrado"));

        Product product = ProductMapper.convertToEntity(productUpdateDTO, Product.class);
        if (image != null && !image.isEmpty()) {
            product.setImageData(convertImageBytesToString(image));
        }

        Product updatedProduct = productRepository.save(product);
        return ProductMapper.convertToDTO(updatedProduct, ProductDTO.class);
    }

    public void delete(Long idProduct) {
        Product product = productRepository.getReferenceById(idProduct);
        productRepository.delete(product);
    }

    private String convertImageBytesToString(MultipartFile image) {
        try {
            byte[] imagemBytes = image.getBytes();
            return Base64.getEncoder().encodeToString(imagemBytes);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar imagem", e);
        }
    }

}
