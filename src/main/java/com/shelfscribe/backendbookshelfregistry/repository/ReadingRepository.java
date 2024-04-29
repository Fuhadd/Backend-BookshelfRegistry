package com.shelfscribe.backendbookshelfregistry.repository;

import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.entity.Favourite;
import com.shelfscribe.backendbookshelfregistry.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findByUserId(Long userId);

    List<Reading> findByUserIdAndBookId(Long userId, Long bookId);
}
