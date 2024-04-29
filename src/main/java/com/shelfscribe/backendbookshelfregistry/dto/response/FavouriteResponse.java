package com.shelfscribe.backendbookshelfregistry.dto.response;
import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteResponse {
    private Long id;
    private User user;
    private Book book;
    private LocalDateTime addedAt;
}
