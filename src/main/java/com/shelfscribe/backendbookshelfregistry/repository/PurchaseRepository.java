package com.shelfscribe.backendbookshelfregistry.repository;

import com.shelfscribe.backendbookshelfregistry.entity.Favourite;
import com.shelfscribe.backendbookshelfregistry.entity.Purchase;
import com.shelfscribe.backendbookshelfregistry.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUserId(Long userId);

    List<Purchase> findByUserIdAndBookId(Long userId, Long bookId);
}
