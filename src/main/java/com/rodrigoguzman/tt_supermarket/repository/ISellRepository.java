package com.rodrigoguzman.tt_supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigoguzman.tt_supermarket.model.Sell;

@Repository
public interface ISellRepository extends JpaRepository<Sell, Long> {
}
