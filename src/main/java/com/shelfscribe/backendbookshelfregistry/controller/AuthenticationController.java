package com.shelfscribe.backendbookshelfregistry.controller;

import com.shelfscribe.backendbookshelfregistry.dto.request.AuthenticationRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.AuthenticationResponse;
import com.shelfscribe.backendbookshelfregistry.service.AuthenticationService;
import com.shelfscribe.backendbookshelfregistry.dto.request.RegisterRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            summary = "Registration endpoint for user"
    )
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));

    }

    @Operation(
            summary = "Login endpoint for user"
    )
    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }

    @GetMapping("/test")
    public ResponseEntity<String> test(
    ){
        return ResponseEntity.ok("HELLO WORLD");

    }

}
