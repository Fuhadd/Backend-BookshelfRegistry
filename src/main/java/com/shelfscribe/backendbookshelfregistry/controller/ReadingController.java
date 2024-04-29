package com.shelfscribe.backendbookshelfregistry.controller;

import com.shelfscribe.backendbookshelfregistry.dto.request.StartReadingRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.UpdateReadingPagesRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.ReadingResponse;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.service.ReadingService;
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
@RequestMapping("/api/v1/reading")
@Tag(name = "Reading")
@AllArgsConstructor
public class ReadingController {
    private final ReadingService readingService;

    @Operation(
            summary = "Start reading book endpoint"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<ReadingResponse>> StartReading(@RequestBody StartReadingRequest startReadingRequest){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(readingService.StartReading(email, startReadingRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get all books started by user endpoint"
    )
    @GetMapping("get-all-user-readings")
    public ResponseEntity<ApiResponse<List<ReadingResponse>>> getAllUserReadings(){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null && !email.equals("anonymousUser")) {
            ApiResponse<List<ReadingResponse>> response =  readingService.getAllUserReadings(email);
            return ResponseEntity.ok(response);
        }

        else {
            throw new CustomException("Could not complete the request. please try again");
        }
    }

    @Operation(
            summary = "Update pages read by user endpoint"
    )
    @PutMapping("update-reading-page")
    public ResponseEntity<ApiResponse<ReadingResponse>> updateReadingPage(@RequestBody UpdateReadingPagesRequest updateReadingPagesRequest){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(readingService.updateReadingPage(updateReadingPagesRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Completes book by user endpoint"
    )
    @PutMapping("complete-book")
    public ResponseEntity<ApiResponse<ReadingResponse>> completeBook(@RequestBody Long readingId){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(readingService.completeBook(readingId), HttpStatus.OK);
    }

    @PostMapping("/remove-reading")
    public ResponseEntity<ApiResponse<Object>> RemoveReading(@RequestBody Long readingId){
        String email =
                SecurityContextHolder.getContext().getAuthentication().getName();
        if (email != null && !email.equals("anonymousUser")) {
            ApiResponse<Object> response =  readingService.RemoveReading(readingId);
            return ResponseEntity.ok(response);
        }

        else {
            throw new CustomException("Could not complete the request. please try again");
        }
    }
}

