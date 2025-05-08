package by.bsuir.navigation.controller;

import by.bsuir.navigation.dto.crud.review.ReviewGetDTO;
import by.bsuir.navigation.dto.crud.review.ReviewPostDTO;
import by.bsuir.navigation.dto.crud.route.RouteGetDTO;
import by.bsuir.navigation.dto.crud.route.RoutePostDTO;
import by.bsuir.navigation.service.impl.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public List<ReviewGetDTO> getAllReviewsByRoute(@PathVariable Long id) {
        return reviewService.getReviewsByRouteId(id);
    }

    @PostMapping("/")
    public void createReview(@RequestBody ReviewPostDTO reviewPostDTO) {
        reviewService.saveReview(reviewPostDTO);
    }

    @PutMapping("/{id}")
    public void putReview(@RequestBody ReviewPostDTO reviewPostDTO, @PathVariable Long id) {
        reviewService.putReview(reviewPostDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
