package com.shelfscribe.backendbookshelfregistry.service.impl;

import com.shelfscribe.backendbookshelfregistry.dto.request.AddFavouriteRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.FavouriteResponse;
import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.entity.Favourite;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.mapper.FavouriteMapper;
import com.shelfscribe.backendbookshelfregistry.repository.BookRepository;
import com.shelfscribe.backendbookshelfregistry.repository.FavouriteRepository;
import com.shelfscribe.backendbookshelfregistry.service.FavouriteService;
import com.shelfscribe.backendbookshelfregistry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final FavouriteRepository favouriteRepository;

    @Override
    public ApiResponse<FavouriteResponse> addFavourite(String userEmail, AddFavouriteRequest favourite) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));

        Book book = bookRepository
                .findById(favourite.getBookId())
                .orElseThrow(() -> new RuntimeException("Book does not exists"));

        Favourite newFavourite = new Favourite(
                null,
                user,
                book,
                LocalDateTime.now()
        );
        Favourite savedFavourite = favouriteRepository.save(newFavourite);
        FavouriteResponse favouriteResponse = FavouriteMapper.mapFavouriteToFavouriteResponse(savedFavourite);
        return new ApiResponse<FavouriteResponse>(
                true,
                "Favourite has been added successfully",
                null,
                favouriteResponse
        );
    }

    @Override
    public ApiResponse<List<FavouriteResponse>> getAllUserFavourites(String userEmail) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));
        List<Favourite> favourites = favouriteRepository.findByUserId(user.getId());

        List<FavouriteResponse> favouriteResponse = favourites.stream()

                .map((FavouriteMapper::mapFavouriteToFavouriteResponse))

                .collect(Collectors.toList());

        return new ApiResponse<List<FavouriteResponse>>(
                true,
                "Favourite has been successfully fetched",
                null,
                favouriteResponse
        );
    }

    @Override
    public ApiResponse<Object> RemoveFavourite(Long id) {
        Favourite favourite = favouriteRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Favourite does not exists"));
        favouriteRepository.deleteById(id);

        return new ApiResponse<Object>(
                true,
                "Favourite has been successfully removed",
                null,
                null

        );
    }
}
