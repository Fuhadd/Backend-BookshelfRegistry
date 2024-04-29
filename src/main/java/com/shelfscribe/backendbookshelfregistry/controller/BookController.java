package com.shelfscribe.backendbookshelfregistry.controller;

import com.shelfscribe.backendbookshelfregistry.dto.request.AddBookRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.GetBooksByCategoryRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.GetBooksByStatusRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.UpdateBookStatusRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookDetailResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookResponse;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@Tag(name = "Book")
@AllArgsConstructor

public class BookController {

    private final BookService bookService;

    @Operation(
            summary = "Add book to shelf endpoint"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<BookResponse>> addBook(@RequestBody AddBookRequest addBookRequest){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(bookService.addBook(email, addBookRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get book by id endpoint"
    )
    @GetMapping("/get-book-by-id")
    public ResponseEntity<ApiResponse<BookDetailResponse>> getBookById(@RequestBody Long id){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        ApiResponse<BookDetailResponse> bookResponse =  bookService.getBookById(email, id);
        return ResponseEntity.ok(bookResponse);
    }

    @Operation(
            summary = "Get User's books by category endpoint"
    )
    @GetMapping("/get-user-books-by-category")
    public ResponseEntity<ApiResponse<List<BookDetailResponse>>> getUserBooksByCategory(@RequestBody GetBooksByCategoryRequest getBooksByCategoryRequest){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Hello, console!,");
        System.out.println(email);
        if (email != null && !email.equals("anonymousUser")) {
            ApiResponse<List<BookDetailResponse>> bookResponse =  bookService.getAllUsersBooksByCategory(email, getBooksByCategoryRequest);
            return ResponseEntity.ok(bookResponse);
        }

        else {
            throw new CustomException("Could not complete the request. please try again");
        }
    }

    @Operation(
            summary = "Get User's books by status endpoint"
    )
    @GetMapping("/get-user-books-by-status")
    public ResponseEntity<ApiResponse<List<BookDetailResponse>>> getUserBooksByStatus(@RequestBody GetBooksByStatusRequest getBooksByStatusRequest){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Hello, console!,");
        System.out.println(email);
        if (email != null && !email.equals("anonymousUser")) {
            ApiResponse<List<BookDetailResponse>> bookResponse =  bookService.getAllUsersBooksByStatus(email, getBooksByStatusRequest);
            return ResponseEntity.ok(bookResponse);
        }
        else {
            throw new CustomException("Could not complete the request. please try again");
        }
    }

    @Operation(
            summary = "Update book status endpoint"
    )
    @PutMapping("/{id}/update-book-status")
    public ResponseEntity<ApiResponse<BookResponse>> updateBookStatus(@PathVariable Long id,
                                                         @RequestBody UpdateBookStatusRequest updateBookStatusRequest){
        ApiResponse<BookResponse> bookResponse =  bookService.updateBookStatus(updateBookStatusRequest);
        return ResponseEntity.ok(bookResponse);
    }

    @Operation(
            summary = "Get All books endpoint"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookDetailResponse>>> getALlBooks() {
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        ApiResponse<List<BookDetailResponse>> books = bookService.getAllBooks(email);
        return ResponseEntity.ok(books);
    }

    @Operation(
            summary = "Delete book by Id endpoint"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }


}
