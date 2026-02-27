package com.rodrigoguzman.tt_supermarket.service;

import java.time.LocalDate;
import java.util.List;

import com.rodrigoguzman.tt_supermarket.dto.SellDTO;

public interface ISellService {
    SellDTO getSellById(Long id);

    void createSell(SellDTO sellDTO);

    List<SellDTO> getSalesByBranchIdAndDate(Long branchId, LocalDate date);

    void deleteSellById(Long id);
}
