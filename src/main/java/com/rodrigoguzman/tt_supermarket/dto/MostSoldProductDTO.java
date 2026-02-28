package com.rodrigoguzman.tt_supermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MostSoldProductDTO {
    private Long productId;
    private String productName;
    private int quantity;
}
