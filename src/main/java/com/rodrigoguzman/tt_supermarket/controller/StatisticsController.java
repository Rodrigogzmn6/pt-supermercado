package com.rodrigoguzman.tt_supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigoguzman.tt_supermarket.dto.MostSoldProductDTO;
import com.rodrigoguzman.tt_supermarket.service.StatisticsService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/most-sold-product")
    public ResponseEntity<MostSoldProductDTO> getMostSoldProduct() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getMostSoldProduct());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
