package by.bsuir.navigation.dto.crud.review;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ReviewGetDTO {
    private Long id;
    private Integer rating;
    private String comment;
    private LocalDate date;
    private List<ReviewImageDTO> reviewImages;
}
