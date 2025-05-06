package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class RouteImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Route route;

    private byte[] image;
}
