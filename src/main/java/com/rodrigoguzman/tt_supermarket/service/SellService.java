package com.rodrigoguzman.tt_supermarket.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigoguzman.tt_supermarket.dto.ProductDTO;
import com.rodrigoguzman.tt_supermarket.dto.SellDTO;
import com.rodrigoguzman.tt_supermarket.model.Branch;
import com.rodrigoguzman.tt_supermarket.model.Product;
import com.rodrigoguzman.tt_supermarket.model.Sell;
import com.rodrigoguzman.tt_supermarket.model.SellDetail;
import com.rodrigoguzman.tt_supermarket.repository.IBranchRepository;
import com.rodrigoguzman.tt_supermarket.repository.IProductRepository;
import com.rodrigoguzman.tt_supermarket.repository.ISellRepository;

@Service
public class SellService implements ISellService {
    @Autowired
    ISellRepository sellRepository;
    @Autowired
    IBranchRepository branchRepository;
    @Autowired
    IProductRepository productRepository;

    @Override
    public SellDTO getSellById(Long id) {
        Sell sell = sellRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No sell found with that id."));
        SellDTO sellDTO = new SellDTO();
        sellDTO.setId(sell.getId());
        sellDTO.setBranchId(sell.getBranch().getId());
        System.out.println("[INFO] Sell details: " + sell.getSellsDetails());
        sellDTO.setProductsDTOs(sell.getSellsDetails().stream().map(sellDetail -> {
            return new ProductDTO(sellDetail.getId(), sellDetail.getProduct().getId(), sellDetail.getQuantity());
        }).toList());
        return sellDTO;
    }

    @Override
    public void createSell(SellDTO sellDTO) {
        if (sellDTO.getBranchId() == null)
            throw new RuntimeException("Branch ID cannot be null.");

        if (sellDTO.getProductsDTOs() == null || sellDTO.getProductsDTOs().isEmpty())
            throw new RuntimeException("The list of products cannot be empty.");

        Branch branch = branchRepository.findById(sellDTO.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("No branch found with that id."));

        List<SellDetail> sellsDetails = new ArrayList<SellDetail>();

        sellDTO.getProductsDTOs().forEach(product -> {
            Product existingProduct = productRepository.findById(product.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("No product found with that id"));

            SellDetail sellDetail = new SellDetail(null, product.getQuantity(), 1.0, null, existingProduct);
            sellsDetails.add(sellDetail);
        });

        Sell sell = new Sell();
        sell.setDate(LocalDate.now());
        sell.setBranch(branch);
        sell.setSellsDetails(sellsDetails);

        // Establecer la relación bidireccional
        sellsDetails.forEach(detail -> detail.setSell(sell));

        System.out.println("[INFO] Created Sell entity: " + sell.getSellsDetails());
        sellRepository.save(sell);
    }

    @Override
    public List<SellDTO> getSalesByBranchIdAndDate(Long branchId, LocalDate date) {
        Branch branch = branchRepository.findById(branchId).orElse(null);

        if (branch == null) {
            throw new RuntimeException("No branch found with that id.");
        }

        List<Sell> sells = sellRepository.findByBranchAndDate(branch, date);

        if (sells.isEmpty()) {
            throw new RuntimeException("No sells found for the given branch and date.");
        }

        List<SellDTO> sellsDTOs = new ArrayList<SellDTO>();

        sells.forEach(sell -> {
            SellDTO sellDTO = new SellDTO();

            sellDTO.setBranchId(sell.getBranch().getId());

            sellDTO.setProductsDTOs(sell.getSellsDetails().stream().map(sellDetail -> {
                return new ProductDTO(null, sellDetail.getProduct().getId(), sellDetail.getQuantity());
            }).toList());

            sellsDTOs.add(sellDTO);
        });

        return sellsDTOs;
    }

    @Override
    public void deleteSellById(Long id) {
        sellRepository.deleteById(id);
    }
}
