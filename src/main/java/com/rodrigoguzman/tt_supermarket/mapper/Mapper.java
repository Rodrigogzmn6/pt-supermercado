package com.rodrigoguzman.tt_supermarket.mapper;

import com.rodrigoguzman.tt_supermarket.dto.BranchDTO;
import com.rodrigoguzman.tt_supermarket.model.Branch;

public class Mapper {
    // Branch to BranchDTO
    public static BranchDTO toDTO(Branch b) {
        if (b == null)
            return null;

        return BranchDTO.builder()
                .id(b.getId())
                .name(b.getName())
                .latitude(b.getLatitude())
                .longitud(b.getLongitud())
                .build();
    }
}
