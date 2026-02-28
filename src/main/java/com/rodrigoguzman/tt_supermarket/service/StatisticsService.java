package com.rodrigoguzman.tt_supermarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigoguzman.tt_supermarket.dto.MostSoldProductDTO;
import com.rodrigoguzman.tt_supermarket.model.Product;
import com.rodrigoguzman.tt_supermarket.repository.ISellDetailRepository;

@Service
public class StatisticsService implements IStatisticsService {
    @Autowired
    private ISellDetailRepository sellDetailRepository;

    @Autowired
    private IProductService productService;

    @Override
    public MostSoldProductDTO getMostSoldProduct() {
        List<Object[]> mostSoldProducts = sellDetailRepository.findMostSoldProductIds();

        if (mostSoldProducts.isEmpty()) {
            throw new RuntimeException("No sells found.");
        }

        Object[] mostSoldProduct = mostSoldProducts.getFirst();

        Long productId = (Long) mostSoldProduct[0];

        Product product = productService.getProductById(productId);

        MostSoldProductDTO productDTO = new MostSoldProductDTO();

        productDTO.setProductId(product.getId());
        productDTO.setProductName(product.getName());
        productDTO.setQuantity(0);

        return productDTO;
    }

}
