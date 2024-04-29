package com.shelfscribe.backendbookshelfregistry.mapper;

import com.shelfscribe.backendbookshelfregistry.dto.response.BookResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.entity.Favourite;
import com.shelfscribe.backendbookshelfregistry.user.User;

import java.time.LocalDateTime;

public class FavouriteMapper {
    public static FavouriteResponse mapFavouriteToFavouriteResponse(Favourite favourite){
        return new FavouriteResponse(
                favourite.getId(),
                favourite.getUser(),
                favourite.getBook(),
                favourite.getAddedAt()
        );
    }
}