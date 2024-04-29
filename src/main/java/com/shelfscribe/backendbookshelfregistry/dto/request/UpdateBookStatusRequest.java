package com.shelfscribe.backendbookshelfregistry.dto.request;
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
public class UpdateBookStatusRequest {

    private Long bookId;
    @Enumerated(EnumType.ORDINAL)
    private BookStatus status;
}
