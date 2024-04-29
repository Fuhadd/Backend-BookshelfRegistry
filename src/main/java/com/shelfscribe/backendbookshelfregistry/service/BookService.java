package com.shelfscribe.backendbookshelfregistry.service;

import com.shelfscribe.backendbookshelfregistry.dto.request.*;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookDetailResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    ApiResponse<BookResponse> addBook(String userEmail, AddBookRequest book);

    ApiResponse<BookDetailResponse> getBookById(String userEmail, Long id);

    ApiResponse<BookResponse> updateBookStatus(UpdateBookStatusRequest updateBookStatusRequest);

    ApiResponse<BookResponse> updateBookRating(UpdateBookRatingRequest updateBookRatingRequest);

    ApiResponse<List<BookDetailResponse>> getAllBooks(String userEmail);

    ApiResponse<List<BookDetailResponse>> getAllUsersBooksByCategory(String userEmail, GetBooksByCategoryRequest getBooksByCategoryRequest);

    ApiResponse<List<BookDetailResponse>> getAllUsersBooksByStatus(String userEmail, GetBooksByStatusRequest getBooksByStatusRequest);


    ApiResponse<List<BookDetailResponse>> getAllUsersBooks(String userEmail);

    void deleteBook(Long id);

}
