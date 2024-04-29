package com.shelfscribe.backendbookshelfregistry.dto.response;
import com.shelfscribe.backendbookshelfregistry.entity.*;
import com.shelfscribe.backendbookshelfregistry.enums.BookCategory;
import com.shelfscribe.backendbookshelfregistry.enums.BookStatus;
import com.shelfscribe.backendbookshelfregistry.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String imageUrl;
    private Double rating;
    private String color;
    private String duration;
    private String language;
    private Integer pages;
    private Double bookRating;
    @Enumerated(EnumType.ORDINAL)
    private BookStatus status;
    @Enumerated(EnumType.ORDINAL)
    private BookCategory category;
    private User user;
    private List<Favourite> favourites;
    private List<Reading> readings;
    private List<Rating> ratings;
//    private List<Cart> cart;
    private List<OrderItem> orderItems;
}
