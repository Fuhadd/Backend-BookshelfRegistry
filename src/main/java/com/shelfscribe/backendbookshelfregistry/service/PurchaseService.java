package com.shelfscribe.backendbookshelfregistry.service;

import com.shelfscribe.backendbookshelfregistry.dto.request.AddFavouriteRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.PurchaseResponse;

import java.util.List;

public interface PurchaseService {
    ApiResponse<PurchaseResponse> purchaseBook(String userEmail, Long bookId);

    ApiResponse<List<PurchaseResponse>> getAllUserPurchases(String userEmail);
}
