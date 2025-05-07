package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data 
public class RouteImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private byte[] image;
}
