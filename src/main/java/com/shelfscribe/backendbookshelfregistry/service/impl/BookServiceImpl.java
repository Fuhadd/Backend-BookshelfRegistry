package com.shelfscribe.backendbookshelfregistry.service.impl;
import com.shelfscribe.backendbookshelfregistry.dto.request.*;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookDetailResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookResponse;
import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.entity.Purchase;
import com.shelfscribe.backendbookshelfregistry.entity.Reading;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.mapper.BookMapper;
import com.shelfscribe.backendbookshelfregistry.repository.BookRepository;
import com.shelfscribe.backendbookshelfregistry.repository.PurchaseRepository;
import com.shelfscribe.backendbookshelfregistry.repository.ReadingRepository;
import com.shelfscribe.backendbookshelfregistry.service.BookService;
import com.shelfscribe.backendbookshelfregistry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService  {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReadingRepository readingRepository;
    private final PurchaseRepository purchaseRepository;


    @Override
    public ApiResponse<BookResponse> addBook(String userEmail, AddBookRequest bookDto)  {

        Book book = BookMapper.mapAddBookRequestToBook(bookDto);
        if(bookDto.getAuthorOnApp()){
            var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));
            List<Book> books =  bookRepository.findByUserId(user.getId());
            book.setUser(user);
        }
        Book savedBook = bookRepository.save(book);
        BookResponse bookResponse = BookMapper.mapBookToBookResponse(savedBook);
        return new ApiResponse<BookResponse>(
                true,
                "Book has been added successfully",
                null,
                bookResponse
        );

    }

    @Override
    public ApiResponse<BookDetailResponse> getBookById(String userEmail, Long id) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));

        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book does not exists"));

//        BookResponse bookResponse =  BookMapper.mapBookToBookResponse(book);

        BookDetailResponse bookDetail= BookMapper.mapBookToBookDetailResponse(book);

        List<Reading> reading = readingRepository.findByUserIdAndBookId(user.getId(), book.getId());
        List<Purchase> purchases = purchaseRepository.findByUserIdAndBookId(user.getId(), book.getId());
        if (!reading.isEmpty()){
            bookDetail.setReading(reading.get(0));
        }

        if (!purchases.isEmpty()){
            bookDetail.setPurchase(purchases.get(0));
        }
        return new ApiResponse<BookDetailResponse>(
                true,
                "Book has been successfully fetched",
                null,
                bookDetail
        );
    }

    @Override
    public ApiResponse<BookResponse> updateBookStatus(UpdateBookStatusRequest updateBookStatusRequest) {
        Book book = bookRepository
                .findById(updateBookStatusRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book does not exists"));
        book.setBookStatus(updateBookStatusRequest.getStatus());
        Book savedBook = bookRepository.save(book);
        BookResponse bookResponse =  BookMapper.mapBookToBookResponse(savedBook);

        return new ApiResponse<BookResponse>(
                true,
                "Book has been successfully fetched",
                null,
                bookResponse
        );
    }

    @Override
    public ApiResponse<BookResponse> updateBookRating(UpdateBookRatingRequest updateBookRatingRequest) {
        Book book = bookRepository
                .findById(updateBookRatingRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book does not exists"));
        var newRating = ((book.getBookRating() + updateBookRatingRequest.getRating()) /10) * 5;
        book.setBookRating(newRating);
        Book savedBook = bookRepository.save(book);
        BookResponse bookResponse =  BookMapper.mapBookToBookResponse(savedBook);

        return new ApiResponse<BookResponse>(
                true,
                "Book rating has been successfully updated",
                null,
                bookResponse
        );
    }

    @Override
    public ApiResponse<List<BookDetailResponse>> getAllBooks(String userEmail) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));

        List<Book> books =  bookRepository.findAll();

        List<BookDetailResponse> bookResponse =  books.stream()
                .map((book -> {
                    BookDetailResponse bookDetail= BookMapper.mapBookToBookDetailResponse(book);

                    List<Reading> reading = readingRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    List<Purchase> purchases = purchaseRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    if (!reading.isEmpty()){
                        bookDetail.setReading(reading.get(0));
                    }

                    if (!purchases.isEmpty()){
                        bookDetail.setPurchase(purchases.get(0));
                    }
                    return bookDetail;
//                    return BookMapper.mapBookToBookResponse(book);
                }))
                .collect(Collectors.toList());

        return new ApiResponse<List<BookDetailResponse>>(
                true,
                "Book has been successfully fetched",
                null,
                bookResponse
        );


    }

    @Override
    public ApiResponse<List<BookDetailResponse>> getAllUsersBooksByCategory(String userEmail, GetBooksByCategoryRequest getBooksByCategoryRequest) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));
        List<Book> books = new ArrayList<>();
        if (getBooksByCategoryRequest.getSearchText().isEmpty()){
            books =  bookRepository.findByUserIdAndBookCategory(user.getId(), getBooksByCategoryRequest.getBookCategory());
        }else{
            books =  bookRepository.findByUserIdAndBookCategoryAndTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(user.getId(), getBooksByCategoryRequest.getBookCategory(), getBooksByCategoryRequest.getSearchText(), getBooksByCategoryRequest.getSearchText());
        }
        List<BookDetailResponse> bookResponse = books.stream()

                .map((book -> {
                    BookDetailResponse bookDetail= BookMapper.mapBookToBookDetailResponse(book);

                    List<Reading> reading = readingRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    List<Purchase> purchases = purchaseRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    if (!reading.isEmpty()){
                        bookDetail.setReading(reading.get(0));
                    }

                    if (!purchases.isEmpty()){
                        bookDetail.setPurchase(purchases.get(0));
                    }
                    return bookDetail;
//                    return BookMapper.mapBookToBookResponse(book);
                }))

                .collect(Collectors.toList());

        return new ApiResponse<List<BookDetailResponse>>(
                true,
                "Book has been successfully fetched",
                null,
                bookResponse
        );
    }

    @Override
    public ApiResponse<List<BookDetailResponse>> getAllUsersBooksByStatus(String userEmail, GetBooksByStatusRequest getBooksByStatusRequest) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));
        List<Book> books = new ArrayList<>();
        if (getBooksByStatusRequest.getSearchText().isEmpty()){
            books =  bookRepository.findByUserIdAndBookStatus(user.getId(), getBooksByStatusRequest.getBookStatus());
        }else{
            books =  bookRepository.findByUserIdAndBookStatusAndTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(user.getId(), getBooksByStatusRequest.getBookStatus(), getBooksByStatusRequest.getSearchText(), getBooksByStatusRequest.getSearchText());
        }
        List<BookDetailResponse> bookResponse = books.stream()

                .map((book -> {
                    BookDetailResponse bookDetail= BookMapper.mapBookToBookDetailResponse(book);

                    List<Reading> reading = readingRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    List<Purchase> purchases = purchaseRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    if (!reading.isEmpty()){
                        bookDetail.setReading(reading.get(0));
                    }

                    if (!purchases.isEmpty()){
                        bookDetail.setPurchase(purchases.get(0));
                    }
                    return bookDetail;

//                    return BookMapper.mapBookToBookResponse(book);
                }))

                .collect(Collectors.toList());

        return new ApiResponse<List<BookDetailResponse>>(
                true,
                "Book has been successfully fetched",
                null,
                bookResponse
        );
    }

    @Override
    public ApiResponse<List<BookDetailResponse>> getAllUsersBooks(String userEmail) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));


        List<Book> books =  bookRepository.findByUserId(user.getId());



        List<BookDetailResponse> bookDetailResponse = books.stream()

                .map((book -> {
                    BookDetailResponse bookDetail= BookMapper.mapBookToBookDetailResponse(book);

                    List<Reading> reading = readingRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    List<Purchase> purchases = purchaseRepository.findByUserIdAndBookId(user.getId(), book.getId());
                    if (!reading.isEmpty()){
                        bookDetail.setReading(reading.get(0));
                    }

                    if (!purchases.isEmpty()){
                        bookDetail.setPurchase(purchases.get(0));
                    }
                    return bookDetail;
                }))

                .collect(Collectors.toList());

        return new ApiResponse<List<BookDetailResponse>>(
                true,
                "Book has been successfully fetched",
                null,
                bookDetailResponse
        );
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Book does not exists"));
        bookRepository.deleteById(id);

    }

}
