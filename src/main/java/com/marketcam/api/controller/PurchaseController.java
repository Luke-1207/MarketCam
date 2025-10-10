package com.marketcam.api.controller;

import com.marketcam.api.dto.PurchaseDTO;
import com.marketcam.api.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra/")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping()
    public ResponseEntity<PurchaseDTO> conclude(@RequestBody PurchaseDTO purchaseDTO){
        return ResponseEntity.ok(purchaseService.conclude(purchaseDTO));
    }

    @GetMapping("{idPurchase}")
    public ResponseEntity<PurchaseDTO> findById(@PathVariable Long idPurchase){
        return ResponseEntity.ok(purchaseService.findById(idPurchase));
    }
}
