package com.shelfscribe.backendbookshelfregistry.mapper;

import com.shelfscribe.backendbookshelfregistry.dto.request.AddBookRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookDetailResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookResponse;
import com.shelfscribe.backendbookshelfregistry.entity.*;

import java.util.ArrayList;

public class BookMapper {

    public static Book mapAddBookRequestToBook(AddBookRequest bookDto){
        return new Book(
                null,
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getDescription(),
                bookDto.getImageUrl(),
                bookDto.getRating(),
                bookDto.getColor(),
                bookDto.getDuration(),
                bookDto.getLanguage(),
                bookDto.getPages(),
                bookDto.getRating(),
                bookDto.getAuthorOnApp(),
                bookDto.getStatus(),
                bookDto.getCategory(),
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
//                new ArrayList<>(),
                new ArrayList<>()
                );
    }
//    public static Book mapUpdateBookStatusRequestToBook(UpdateBookStatusRequest bookDto){
//        return new Book(
//                null,
//               null,
//               null,
//               null,
//               null,
//               null,
//               null,
//               null,
//               null,
//               null,
//                bookDto.getStatus(),
//                null
//        );
//    }

    public static BookResponse mapBookToBookResponse(Book book){
        return new BookResponse(
                book.getId(),
               book.getTitle(),
               book.getAuthor(),
               book.getDescription(),
               book.getImageUrl(),
               book.getRating(),
               book.getColor(),
               book.getDuration(),
               book.getLanguage(),
               book.getPages(),
                book.getRating(),
                book.getBookStatus(),
               book.getBookCategory(),
                book.getUser(),
                book.getFavourites(),
                book.getReadings(),
                book.getRatings(),
                book.getOrderItems()
        );
    }

    public static BookDetailResponse mapBookToBookDetailResponse(Book book){
        return new BookDetailResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getImageUrl(),
                book.getRating(),
                book.getColor(),
                book.getDuration(),
                book.getLanguage(),
                book.getPages(),
                book.getBookStatus(),
                book.getBookCategory(),
                book.getUser(),
                null,
                null
        );
    }
}
