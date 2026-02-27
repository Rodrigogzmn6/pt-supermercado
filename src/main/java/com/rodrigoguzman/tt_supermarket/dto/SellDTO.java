package com.rodrigoguzman.tt_supermarket.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellDTO {
    private Long id, branchId;
    private List<ProductDTO> productsDTOs;
}
