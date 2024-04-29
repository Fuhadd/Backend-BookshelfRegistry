package com.shelfscribe.backendbookshelfregistry.service;

import com.shelfscribe.backendbookshelfregistry.dto.request.RegisterRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.AuthenticationRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.AuthenticationResponse;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.enums.Role;
import com.shelfscribe.backendbookshelfregistry.user.User;
import com.shelfscribe.backendbookshelfregistry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ApiResponse<AuthenticationResponse> register(RegisterRequest request) {
        try {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            AuthenticationResponse authResponse =  AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();

            return new ApiResponse<AuthenticationResponse>(
                    true,
                    "User registration completed successfull",
                    null,
                    authResponse
            );
        }catch (Exception ex){
            throw new CustomException(ex.getMessage());
        }
    }

    public ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            //Getting to the next line means the user has been authenticated
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));
            var jwtToken = jwtService.generateToken(user);
            System.out.print(jwtToken);

            AuthenticationResponse authResponse =  AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(user)
                    .build();

            return new ApiResponse<AuthenticationResponse>(
                    true,
                    "Login successfully completed",
                    null,
                    authResponse
            );

        }catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }
}
