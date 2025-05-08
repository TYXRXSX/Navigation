package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Users users;

    private Integer rating;
    private String comment;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewImage> reviewImages = new ArrayList<>();


    public void addReviewImage(ReviewImage reviewImage) {
       reviewImages.add(reviewImage);
       reviewImage.setReview(this);
    }

    public void removeReviewImage(ReviewImage reviewImage) {
        reviewImages.remove(reviewImage);
        reviewImage.setReview(this);
    }

    public void removeAllImages() {
        for (ReviewImage image : new ArrayList<>(reviewImages)) {
            removeReviewImage(image);
        }
        reviewImages.clear();
    }
}
