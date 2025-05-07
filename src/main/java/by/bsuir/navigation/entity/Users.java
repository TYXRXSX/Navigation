package by.bsuir.navigation.entity;

import by.bsuir.navigation.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String email;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private byte[] avatar;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_routes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    private Set<Route> favoriteRoutes = new HashSet<>();

    public void addFavoriteRoute(Route route) {
        favoriteRoutes.add(route);
        route.getFavoritedBy().add(this);
    }

    public void removeFavoriteRoute(Route route) {
        favoriteRoutes.remove(route);
        route.getFavoritedBy().remove(this);
    }
}
