package com.shelfscribe.backendbookshelfregistry.mapper;

import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.PurchaseResponse;
import com.shelfscribe.backendbookshelfregistry.entity.Favourite;
import com.shelfscribe.backendbookshelfregistry.entity.Purchase;

public class PurchaseMapper {
    public static PurchaseResponse mapPurchaseToPurchaseResponse(Purchase purchase){
        return new PurchaseResponse(
                purchase.getId(),
                purchase.getUser(),
                purchase.getBook(),
                purchase.getPurchasedAt()
        );
    }
}
