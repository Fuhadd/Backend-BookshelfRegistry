package com.shelfscribe.backendbookshelfregistry.controller;

import com.shelfscribe.backendbookshelfregistry.dto.request.AddFavouriteRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.service.FavouriteService;
import com.shelfscribe.backendbookshelfregistry.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/wallet")
@Tag(name = "Wallet")
@AllArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @Operation(
            summary = "Create user wallet endpoint"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createUserWallet(){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(walletService.createUserWallet(email), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Top up user's wallet endpoint"
    )
    @PutMapping("/fund")
    public ResponseEntity<ApiResponse<Object>> fundUserWallet(@RequestBody Double amount){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null && !email.equals("anonymousUser")) {
            ApiResponse<Object> response =  walletService.fundUserWallet(email, amount);
            return ResponseEntity.ok(response);
        }

        else {
            throw new CustomException("Could not complete the request. please try again");
        }
    }
}
