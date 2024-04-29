package com.shelfscribe.backendbookshelfregistry.entity;
import com.shelfscribe.backendbookshelfregistry.enums.BookCategory;
import com.shelfscribe.backendbookshelfregistry.enums.BookStatus;
import com.shelfscribe.backendbookshelfregistry.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @Lob
    @Size(max = 2000)
    private String description;
    private String imageUrl;
    private Double rating;
    private String color;
    private String duration;
    private String language;
    private Integer pages;
    private Double bookRating;
    private Boolean authorOnApp;
    @Enumerated(EnumType.ORDINAL)
    private BookStatus bookStatus;
    @Enumerated(EnumType.ORDINAL)
    private BookCategory bookCategory;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Favourite> favourites;


    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Reading> readings;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Rating> ratings;

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    private List<Cart> cart;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


}