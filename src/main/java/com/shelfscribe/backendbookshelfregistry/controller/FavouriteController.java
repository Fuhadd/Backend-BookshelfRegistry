package com.shelfscribe.backendbookshelfregistry.controller;

import com.shelfscribe.backendbookshelfregistry.dto.request.*;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.service.FavouriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/favourite")
@Tag(name = "Favourite")
@AllArgsConstructor
public class FavouriteController {
    private final FavouriteService favouriteService;

    @Operation(
            summary = "Add book to User's favourite endpoint"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<FavouriteResponse>> addFavourite(@RequestBody AddFavouriteRequest addFavouriteRequest){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(favouriteService.addFavourite(email, addFavouriteRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all User's favourite endpoint"
    )
    @GetMapping("/get-all-user-favourites")
    public ResponseEntity<ApiResponse<List<FavouriteResponse>>> getAllUserFavourites(){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null && !email.equals("anonymousUser")) {
            ApiResponse<List<FavouriteResponse>> favouriteResponse =  favouriteService.getAllUserFavourites(email);
            return ResponseEntity.ok(favouriteResponse);
        }
        else {
            throw new CustomException("Could not complete the request. please try again");
        }
    }

    @Operation(
            summary = "AdRemoved book from User's favourite endpoint"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> RemoveFavourite(@PathVariable Long id) {
        ApiResponse<Object> response = favouriteService.RemoveFavourite(id);
        return ResponseEntity.ok(response);
    }
}
