package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouteImage> routeImages = new ArrayList<>();

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouteWaypoint> routeWaypoints = new ArrayList<>();

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "favoriteRoutes")
    private Set<Users> favoritedBy = new HashSet<>();

    public void addWaypoint(RouteWaypoint waypoint) {
        routeWaypoints.add(waypoint);
        waypoint.setRoute(this);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setRoute(this);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setRoute(null);
    }

    public void addRouteImage(RouteImage routeImage) {
        routeImages.add(routeImage);
        routeImage.setRoute(this);
    }

    public void removeRouteImage(RouteImage routeImage) {
        routeImages.remove(routeImage);
        routeImage.setRoute(null);
    }

    public void removeWaypoint(RouteWaypoint waypoint) {
        routeWaypoints.remove(waypoint);
        waypoint.setRoute(null);
    }

    public void addFavoritedBy(Users user) {
        favoritedBy.add(user);
        user.getFavoriteRoutes().add(this);
    }

    public void removeFavoritedBy(Users user) {
        favoritedBy.remove(user);
        user.getFavoriteRoutes().remove(this);
    }

    public void removeAllWaypoints() {
        for (RouteWaypoint waypoint : new ArrayList<>(routeWaypoints)) {
            removeWaypoint(waypoint);
        }
        routeWaypoints.clear();
    }

    public void removeAllImages() {
        for (RouteImage image : new ArrayList<>(routeImages)) {
            removeRouteImage(image);
        }
        routeImages.clear();
    }
}

