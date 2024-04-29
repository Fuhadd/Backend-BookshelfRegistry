//package com.shelfscribe.backendbookshelfregistry.entity;
//
//import com.shelfscribe.backendbookshelfregistry.enums.BookCategory;
//import com.shelfscribe.backendbookshelfregistry.enums.BookStatus;
//import com.shelfscribe.backendbookshelfregistry.user.User;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "Cart")
//public class Cart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long cartId;
//    private Integer quantity;
//    private LocalDateTime addedAt;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id", nullable = false)
//    private Book book;
//
//
//}
