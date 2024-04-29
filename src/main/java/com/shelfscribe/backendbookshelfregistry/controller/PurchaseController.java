package com.shelfscribe.backendbookshelfregistry.controller;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.PurchaseResponse;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchase")
@Tag(name = "Purchase")
@AllArgsConstructor

public class PurchaseController {
    private final PurchaseService purchaseService;

    @Operation(
            summary = "Purchase book endpoint"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<PurchaseResponse>> purchaseBook(@RequestBody Long bookId){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(purchaseService.purchaseBook(email, bookId), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all user's purchases endpoint"
    )
    @GetMapping("/get-all-user-purchase")
    public ResponseEntity<ApiResponse<List<PurchaseResponse>>> getAllUserPurchases(){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null && !email.equals("anonymousUser")) {
            ApiResponse<List<PurchaseResponse>> purchaseResponse =  purchaseService.getAllUserPurchases(email);
            return ResponseEntity.ok(purchaseResponse);
        }
        else {
            throw new CustomException("Could not complete the request. please try again");
        }
    }
}