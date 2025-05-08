package by.bsuir.navigation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

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

    @ManyToMany(mappedBy = "favoriteRoutes", cascade = CascadeType.ALL)
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
//        user.getFavoriteRoutes().add(this);
    }

    public void removeFavoritedBy(Users user) {
        favoritedBy.remove(user);
//        user.getFavoriteRoutes().remove(this);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(name, route.name) && Objects.equals(latitude, route.latitude) && Objects.equals(longitude, route.longitude) && Objects.equals(description, route.description) && Objects.equals(type, route.type) && Objects.equals(surface, route.surface) && Objects.equals(length, route.length) && Objects.equals(difficulty, route.difficulty) && Objects.equals(duration, route.duration) && Objects.equals(weather, route.weather) && Objects.equals(routeImages, route.routeImages) && Objects.equals(routeWaypoints, route.routeWaypoints) && Objects.equals(reviews, route.reviews) && Objects.equals(favoritedBy, route.favoritedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude, description, type, surface, length, difficulty, duration, weather);
    }
}

