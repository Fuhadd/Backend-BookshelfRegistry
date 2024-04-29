package com.shelfscribe.backendbookshelfregistry.service;

import com.shelfscribe.backendbookshelfregistry.dto.request.AddFavouriteRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.StartReadingRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.UpdateReadingPagesRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.ReadingResponse;

import java.util.List;

public interface ReadingService {
    ApiResponse<ReadingResponse> StartReading(String userEmail, StartReadingRequest startReadingRequest);

    ApiResponse<List<ReadingResponse>> getAllUserReadings(String userEmail);

    ApiResponse<ReadingResponse> updateReadingPage(UpdateReadingPagesRequest updateReadingPagesRequest);

    ApiResponse<ReadingResponse> completeBook(Long id);


    ApiResponse<Object> RemoveReading(Long id);
}
