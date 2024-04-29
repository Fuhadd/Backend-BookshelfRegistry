package com.shelfscribe.backendbookshelfregistry.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRatingRequest {

    private Long bookId;
    private Double rating;
}
