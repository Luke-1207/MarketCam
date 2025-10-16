package com.marketcam.api.repository;

import com.marketcam.api.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query("SELECT DISTINCT p FROM Purchase p LEFT JOIN FETCH p.items i LEFT JOIN FETCH i.product")
    List<Purchase> findAllWithItems();
}
