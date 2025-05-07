package by.bsuir.navigation.repo;

import by.bsuir.navigation.entity.Route;
import by.bsuir.navigation.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}
