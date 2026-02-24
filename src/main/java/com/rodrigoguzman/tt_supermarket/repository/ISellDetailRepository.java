package com.rodrigoguzman.tt_supermarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigoguzman.tt_supermarket.model.SellDetail;

@Repository
public interface ISellDetailRepository extends JpaRepository<SellDetail, Long> {
}
