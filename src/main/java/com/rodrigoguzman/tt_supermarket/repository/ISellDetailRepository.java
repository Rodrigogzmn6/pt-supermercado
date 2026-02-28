package com.rodrigoguzman.tt_supermarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rodrigoguzman.tt_supermarket.model.SellDetail;

@Repository
public interface ISellDetailRepository extends JpaRepository<SellDetail, Long> {
    @Query("""
                SELECT sd.product.id, SUM(sd.quantity) as totalQuantity
                FROM SellDetail sd
                GROUP BY sd.product.id
                ORDER BY SUM(sd.quantity) DESC
            """)
    List<Object[]> findMostSoldProductIds();
}
