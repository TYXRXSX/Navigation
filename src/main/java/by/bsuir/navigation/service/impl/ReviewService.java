package by.bsuir.navigation.service.impl;

import by.bsuir.navigation.dto.crud.review.ReviewGetDTO;
import by.bsuir.navigation.dto.crud.review.ReviewImageDTO;
import by.bsuir.navigation.dto.crud.review.ReviewPostDTO;
import by.bsuir.navigation.dto.crud.route.RouteGetDTO;
import by.bsuir.navigation.entity.Review;
import by.bsuir.navigation.entity.ReviewImage;
import by.bsuir.navigation.entity.Route;
import by.bsuir.navigation.repo.ReviewRepository;
import by.bsuir.navigation.repo.RouteRepository;
import by.bsuir.navigation.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService implements CrudService {
    private final ReviewRepository reviewRepository;
    private final RouteRepository routeRepository;

    public List<ReviewGetDTO> getReviewsByRouteId(Long id) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Route not found"));
        return route.getReviews().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void saveReview(ReviewPostDTO reviewPostDTO) {
        Route route = routeRepository.findById(reviewPostDTO.getRouteId()).orElseThrow(() -> new RuntimeException("Route not found"));
        route.addReview(Review.builder()
                .rating(reviewPostDTO.getRating())
                .date(reviewPostDTO.getDate())
                .comment(reviewPostDTO.getComment())
                .reviewImages(reviewPostDTO.getReviewImages().stream().map(x -> new ReviewImage(x.getImage())).collect(Collectors.toList()))
                .build());

        routeRepository.save(route);
    }

    public void putReview(ReviewPostDTO reviewPostDTO, Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.removeAllImages();
        review.setRating(reviewPostDTO.getRating());
        review.setDate(reviewPostDTO.getDate());
        review.setComment(reviewPostDTO.getComment());
        reviewPostDTO.getReviewImages()
                .forEach(reviewImageDTO -> {
                    review.addReviewImage(new ReviewImage(reviewImageDTO.getImage()));
                });
        reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    private ReviewGetDTO mapToDTO(Review review) {
        return ReviewGetDTO.builder()
                .id(review.getId())
                .date(review.getDate())
                .comment(review.getComment())
                .rating(review.getRating())
                .reviewImages(review.getReviewImages().stream().map(x -> new ReviewImageDTO(x.getImage())).collect(Collectors.toList()))
                .build();
    }
}
