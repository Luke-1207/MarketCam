package com.marketcam.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketcam.api.dto.ProductCreateDTO;
import com.marketcam.api.dto.ProductDTO;
import com.marketcam.api.dto.ProductUpdateDTO;
import com.marketcam.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/produto/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDTO> create(
            @RequestPart("produto") String productCreateDTOString,
            @RequestPart(value = "imagem") MultipartFile image
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductCreateDTO productCreateDTO = objectMapper.readValue(productCreateDTOString, ProductCreateDTO.class);

        return ResponseEntity.ok(productService.create(productCreateDTO, image));
    }

    @GetMapping("{idProduct}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer idProduct){
        return ResponseEntity.ok(productService.findById(idProduct));
    }

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> update(
            @RequestPart("produto") String productUpdateDTOString,
            @RequestPart(value = "imagem", required = false) MultipartFile image
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductUpdateDTO productUpdateDTO = objectMapper.readValue(productUpdateDTOString, ProductUpdateDTO.class);

        return ResponseEntity.ok(productService.update(productUpdateDTO, image));
    }

    @DeleteMapping("{idProduct}")
    public ResponseEntity delete(@PathVariable Integer idProduct){
        productService.delete(idProduct);
        return ResponseEntity.ok().build();
    }
}
