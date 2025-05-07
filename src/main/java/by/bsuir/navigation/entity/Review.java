package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

}
