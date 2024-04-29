package com.shelfscribe.backendbookshelfregistry.repository;

import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.enums.BookCategory;
import com.shelfscribe.backendbookshelfregistry.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserId(Long userId);

    List<Book> findByUserIdAndBookCategory(Long userId, BookCategory bookCategory);

    List<Book> findByUserIdAndBookStatus(Long userId, BookStatus bookStatus);

    // In your BookRepository interface

    List<Book> findByUserIdAndBookCategoryAndTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(Long userId, BookCategory bookCategory, String title, String author);

    List<Book> findByUserIdAndBookStatusAndTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(Long userId, BookStatus bookStatus, String title, String author);

}
