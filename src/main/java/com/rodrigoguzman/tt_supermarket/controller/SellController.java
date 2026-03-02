package com.rodrigoguzman.tt_supermarket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigoguzman.tt_supermarket.dto.SellDTO;
import com.rodrigoguzman.tt_supermarket.service.ISellService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/sells")
public class SellController {
    @Autowired
    private ISellService sellService;

    @PostMapping
    public ResponseEntity<String> createSell(@RequestBody SellDTO sellDTO) {
        try {
            sellService.createSell(sellDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sell created successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SellDTO> getSellById(@PathVariable Long id) {
        try {
            SellDTO sellDTO = sellService.getSellById(id);
            return ResponseEntity.ok(sellDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SellDTO>> getSellByBranchIdAndDate(@RequestParam Long branchId,
            @RequestParam LocalDate date) {
        try {
            List<SellDTO> sells = sellService.getSalesByBranchIdAndDate(branchId, date);
            return ResponseEntity.status(HttpStatus.OK).body(sells);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSellById(@PathVariable Long id) {
        try {
            sellService.deleteSellById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Sell deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
