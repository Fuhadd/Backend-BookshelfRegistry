package com.shelfscribe.backendbookshelfregistry.dto.request;

import com.shelfscribe.backendbookshelfregistry.enums.BookCategory;
import com.shelfscribe.backendbookshelfregistry.enums.BookStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {
    private String title;
    private String author;
    private String description;
    private String imageUrl;
    private Double rating;
    private String color;
    private String duration;
    private String language;
    private Integer pages;
    private Boolean authorOnApp;
    @Enumerated(EnumType.ORDINAL)
    private BookStatus status;
    @Enumerated(EnumType.ORDINAL)
    private BookCategory category;
}
