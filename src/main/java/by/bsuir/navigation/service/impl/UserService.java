package by.bsuir.navigation.service.impl;

import by.bsuir.navigation.dto.auth.RegisterDTO;
import by.bsuir.navigation.dto.crud.route.RouteGetDTO;
import by.bsuir.navigation.dto.crud.route.RouteImageDTO;
import by.bsuir.navigation.dto.crud.route.RouteWaypointDTO;
import by.bsuir.navigation.dto.crud.user.UserChangeDataDTO;
import by.bsuir.navigation.entity.Route;
import by.bsuir.navigation.entity.Users;
import by.bsuir.navigation.repo.RouteRepository;
import by.bsuir.navigation.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RouteRepository routeRepository;

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void register(RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        Users user = new Users();
        user.setUsername(registerDTO.getUsername());
        user.setPasswordHash(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setAvatar(registerDTO.getAvatar());
        userRepository.save(user);
    }

    public void addToFavorite(Long id){
        Route route = routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Route not found"));
        Users user = findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.addFavoriteRoute(route);
        userRepository.save(user);
        routeRepository.save(route);
    }

    public void removeFromFavorite(Long id){
        Route route = routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Route not found"));
        Users user = findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.removeFavoriteRoute(route);
        userRepository.save(user);
        routeRepository.save(route);
    }

    public List<RouteGetDTO> getFavoriteRoutes(){
        Users user = findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.getFavoriteRoutes().stream().map(route -> mapToDTO(route)).collect(Collectors.toList());
    }

    private RouteGetDTO mapToDTO(Route route) {
        RouteGetDTO routeGetDTO = RouteGetDTO.builder()
                .id(route.getId())
                .description(route.getDescription())
                .weather(route.getWeather())
                .difficulty(route.getDifficulty())
                .duration(route.getDuration())
                .type(route.getType())
                .name(route.getName())
                .latitude(route.getLatitude())
                .longitude(route.getLongitude())
                .surface(route.getSurface())
                .length(route.getLength())
                .images(route.getRouteImages().stream().map(x -> new RouteImageDTO(x.getImage())).collect(Collectors.toList()))
                .waypoints(route.getRouteWaypoints().stream().map(x -> new RouteWaypointDTO(x.getLatitude(), x.getLongitude())).toList())
                .build();

        return routeGetDTO;
    }

    public void changePersonalData(UserChangeDataDTO userChangeDataDTO){
        Users user = findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setAvatar(userChangeDataDTO.getAvatar());
        user.setEmail(userChangeDataDTO.getEmail());
        user.setName(userChangeDataDTO.getName());
        user.setPasswordHash(passwordEncoder.encode(userChangeDataDTO.getPassword()));
        userRepository.save(user);
    }
}
