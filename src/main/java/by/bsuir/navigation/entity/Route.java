package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String description;
    private String type;
    private String surface;
    private Double length;
    private String difficulty;
    private String duration;
    private String weather;

    @OneToMany
    private List<RouteImage> routeImages;

    @OneToMany
    private List<RouteWaypoint> routeWaypoints;

    @OneToMany
    private List<Review> reviews;
}
