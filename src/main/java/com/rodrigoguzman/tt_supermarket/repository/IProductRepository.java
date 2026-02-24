package com.rodrigoguzman.tt_supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigoguzman.tt_supermarket.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
