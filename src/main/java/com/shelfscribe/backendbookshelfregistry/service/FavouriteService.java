package com.shelfscribe.backendbookshelfregistry.service;

import com.shelfscribe.backendbookshelfregistry.dto.request.*;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.BookResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;

import java.util.List;

public interface FavouriteService {
    ApiResponse<FavouriteResponse> addFavourite(String userEmail, AddFavouriteRequest favourite);

    ApiResponse<List<FavouriteResponse>> getAllUserFavourites(String userEmail);

    ApiResponse<Object> RemoveFavourite(Long id);
}
