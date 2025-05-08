package by.bsuir.navigation.entity;

import by.bsuir.navigation.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_favorite_routes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    private Set<Route> favoriteRoutes = new HashSet<>();

    public void addFavoriteRoute(Route route) {
        if (favoriteRoutes == null) {
            favoriteRoutes = new HashSet<>();
        }
        favoriteRoutes.add(route);
        route.getFavoritedBy().add(this);
    }

    public void removeFavoriteRoute(Route route) {
        if (favoriteRoutes == null) {
            favoriteRoutes = new HashSet<>();
        }
        favoriteRoutes.remove(route);
        route.getFavoritedBy().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) && Objects.equals(name, users.name) && Objects.equals(username, users.username) && Objects.equals(email, users.email) && Objects.equals(passwordHash, users.passwordHash) && role == users.role && Objects.deepEquals(avatar, users.avatar) && Objects.equals(favoriteRoutes, users.favoriteRoutes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, email, passwordHash, role, favoriteRoutes);
    }
}
