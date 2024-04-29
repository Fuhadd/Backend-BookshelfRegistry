package com.shelfscribe.backendbookshelfregistry.service.impl;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.PurchaseResponse;
import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.entity.Purchase;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.mapper.PurchaseMapper;
import com.shelfscribe.backendbookshelfregistry.repository.BookRepository;
import com.shelfscribe.backendbookshelfregistry.repository.PurchaseRepository;
import com.shelfscribe.backendbookshelfregistry.service.PurchaseService;
import com.shelfscribe.backendbookshelfregistry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    @Override
    public ApiResponse<PurchaseResponse> purchaseBook(String userEmail, Long bookId) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));

        Book book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book does not exists"));

        Purchase newPurchase = new Purchase(
                null,
                user,
                book,
                LocalDateTime.now()
        );
        Purchase savedPurchase = purchaseRepository.save(newPurchase);
        PurchaseResponse purchaseResponse = PurchaseMapper.mapPurchaseToPurchaseResponse(savedPurchase);
        return new ApiResponse<PurchaseResponse>(
                true,
                "Purchase has been completed successfully",
                null,
                purchaseResponse
        );
    }

    @Override
    public ApiResponse<List<PurchaseResponse>> getAllUserPurchases(String userEmail) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));
        List<Purchase> purchases = purchaseRepository.findByUserId(user.getId());

        List<PurchaseResponse> purchaseResponse = purchases.stream()

                .map((PurchaseMapper::mapPurchaseToPurchaseResponse))

                .collect(Collectors.toList());

        return new ApiResponse<List<PurchaseResponse>>(
                true,
                "Purchases has been successfully fetched",
                null,
                purchaseResponse
        );
    }
}
