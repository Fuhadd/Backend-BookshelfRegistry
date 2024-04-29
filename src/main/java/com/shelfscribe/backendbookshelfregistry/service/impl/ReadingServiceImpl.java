package com.shelfscribe.backendbookshelfregistry.service.impl;
import com.shelfscribe.backendbookshelfregistry.dto.request.StartReadingRequest;
import com.shelfscribe.backendbookshelfregistry.dto.request.UpdateReadingPagesRequest;
import com.shelfscribe.backendbookshelfregistry.dto.response.ApiResponse;
import com.shelfscribe.backendbookshelfregistry.dto.response.ReadingResponse;
import com.shelfscribe.backendbookshelfregistry.entity.Book;
import com.shelfscribe.backendbookshelfregistry.entity.Reading;
import com.shelfscribe.backendbookshelfregistry.exceptions.CustomException;
import com.shelfscribe.backendbookshelfregistry.mapper.ReadingMapper;
import com.shelfscribe.backendbookshelfregistry.repository.BookRepository;
import com.shelfscribe.backendbookshelfregistry.repository.FavouriteRepository;
import com.shelfscribe.backendbookshelfregistry.repository.ReadingRepository;
import com.shelfscribe.backendbookshelfregistry.service.ReadingService;
import com.shelfscribe.backendbookshelfregistry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReadingServiceImpl implements ReadingService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final FavouriteRepository favouriteRepository;
    private final ReadingRepository readingRepository;

    @Override
    public ApiResponse<ReadingResponse> StartReading(String userEmail, StartReadingRequest startReadingRequest) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));

        Book book = bookRepository
                .findById(startReadingRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book does not exists"));

        Reading newReading = new Reading(
                null,
                1,
                LocalDateTime.now(),
                null,
                false,
                user,
                book
        );

        Reading savedReading = readingRepository.save(newReading);
        ReadingResponse readingResponse = ReadingMapper.mapReadingToReadingResponse(savedReading);
        return new ApiResponse<ReadingResponse>(
                true,
                "Reading has been updated successfully",
                null,
                readingResponse
        );
    }

    @Override
    public ApiResponse<List<ReadingResponse>> getAllUserReadings(String userEmail) {
        var user = userRepository.findByEmail(userEmail).orElseThrow(() -> new CustomException("User does not exist. Please proceed to registeration"));
        List<Reading> readings = readingRepository.findByUserId(user.getId());

        List<ReadingResponse> readingResponse = readings.stream()

                .map((ReadingMapper::mapReadingToReadingResponse))

                .collect(Collectors.toList());

        return new ApiResponse<List<ReadingResponse>>(
                true,
                "Reading has been successfully fetched",
                null,
                readingResponse
        );
    }

    @Override
    public ApiResponse<ReadingResponse> updateReadingPage(UpdateReadingPagesRequest updateReadingPagesRequest) {
        Reading reading = readingRepository
                .findById(updateReadingPagesRequest.getId())
                .orElseThrow(() -> new RuntimeException("You have not started reading"));

        reading.setPagesCompleted(updateReadingPagesRequest.getPagesCompleted());
        Reading savedReading = readingRepository.save(reading);
        ReadingResponse readingResponse =  ReadingMapper.mapReadingToReadingResponse(savedReading);

        return new ApiResponse<ReadingResponse>(
                true,
                "Book has been successfully completed",
                null,
                readingResponse
        );
    }

    @Override
    public ApiResponse<ReadingResponse> completeBook(Long id) {
        Reading reading = readingRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("You have not started reading"));

        reading.setCompleted(true);
        Reading savedReading = readingRepository.save(reading);
        ReadingResponse readingResponse =  ReadingMapper.mapReadingToReadingResponse(savedReading);

        return new ApiResponse<ReadingResponse>(
                true,
                "Book has been successfully completed",
                null,
                readingResponse
        );
    }

    @Override
    public ApiResponse<Object> RemoveReading(Long id) {
        Reading reading = readingRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("You have not started reading "));
        readingRepository.deleteById(reading.getId());

        return new ApiResponse<Object>(
                true,
                "Reading has been successfully removed",
                null,
                null

        );
    }
}
