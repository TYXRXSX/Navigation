package by.bsuir.navigation.dto.crud.review;

import by.bsuir.navigation.entity.Users;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ReviewPostDTO {
    private Integer rating;
    private String comment;
    private LocalDate date;
    private Long routeId;
    private List<ReviewImageDTO> reviewImages;
}
