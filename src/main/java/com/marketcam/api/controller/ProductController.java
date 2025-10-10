package com.marketcam.api.controller;

import com.marketcam.api.dto.ProductDTO;
import com.marketcam.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    public ResponseEntity<Boolean> create(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.create(productDTO));
    }

    @GetMapping("{idProduct}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long idProduct){
        return ResponseEntity.ok(productService.findById(idProduct));
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping()
    public ResponseEntity<Boolean> update(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.update(productDTO));
    }

    @DeleteMapping("{idProduct}")
    public ResponseEntity<Boolean> update(@PathVariable Long idProduct){
        return ResponseEntity.ok(productService.delete(idProduct));
    }
}
