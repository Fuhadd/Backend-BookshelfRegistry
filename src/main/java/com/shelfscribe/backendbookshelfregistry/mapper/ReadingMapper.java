package com.shelfscribe.backendbookshelfregistry.mapper;

import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.ReadingResponse;
import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.entity.Favourite;
import com.shelfscribe.backendbookshelfregistry.entity.Reading;
import com.shelfscribe.backendbookshelfregistry.user.User;

import java.time.LocalDateTime;

public class ReadingMapper {
    public static ReadingResponse mapReadingToReadingResponse(Reading reading){
        return new ReadingResponse(
                reading.getId(),
                reading.getPagesCompleted(),
                reading.getStartedAt(),
                reading.getFinishedAt(),
                reading.getUser(),
                reading.getBook()
        );
    }
}