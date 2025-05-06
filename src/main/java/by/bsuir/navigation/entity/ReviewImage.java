package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class ReviewImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Review review;

    private byte[] image;
}
