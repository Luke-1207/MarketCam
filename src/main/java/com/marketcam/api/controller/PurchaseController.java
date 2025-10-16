package com.marketcam.api.controller;

import com.marketcam.api.dto.PurchaseCreateDTO;
import com.marketcam.api.dto.PurchaseDTO;
import com.marketcam.api.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra/")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping()
    public ResponseEntity<List<PurchaseDTO>> findAll(){
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @PostMapping()
    public ResponseEntity<PurchaseDTO> create(@RequestBody PurchaseCreateDTO purchaseCreateDTO){
        return ResponseEntity.ok(purchaseService.create(purchaseCreateDTO));
    }

    @DeleteMapping("{idPurchase}")
    public ResponseEntity delete(@PathVariable Integer idPurchase){
        purchaseService.delete(idPurchase);
        return ResponseEntity.ok().build();
    }
}
