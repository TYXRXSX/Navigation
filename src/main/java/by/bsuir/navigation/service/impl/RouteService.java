package by.bsuir.navigation.service.impl;

import by.bsuir.navigation.dto.crud.route.RouteGetDTO;
import by.bsuir.navigation.dto.crud.route.RouteImageDTO;
import by.bsuir.navigation.dto.crud.route.RoutePostDTO;
import by.bsuir.navigation.dto.crud.route.RouteWaypointDTO;
import by.bsuir.navigation.entity.Route;
import by.bsuir.navigation.entity.RouteImage;
import by.bsuir.navigation.entity.RouteWaypoint;
import by.bsuir.navigation.repo.RouteRepository;
import by.bsuir.navigation.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService implements CrudService {

    private final RouteRepository routeRepository;

    public RouteGetDTO getRoute(Long id) {
        Route route = routeRepository.findById(id).get();
        RouteGetDTO routeGetDTO = mapToDTO(route);

        return routeGetDTO;
    }

    public List<RouteGetDTO> getAllRoutes() {
        return routeRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public void saveRoute(RoutePostDTO routePostDTO) {
        Route route = new Route();
        route.setDescription(routePostDTO.getDescription());
        route.setDuration(routePostDTO.getDuration());
        route.setDifficulty(routePostDTO.getDifficulty());
        route.setLatitude(routePostDTO.getLatitude());
        route.setLongitude(routePostDTO.getLongitude());
        route.setLength(routePostDTO.getLength());
        route.setName(routePostDTO.getName());
        route.setWeather(routePostDTO.getWeather());
        route.setSurface(routePostDTO.getSurface());
        route.setType(routePostDTO.getType());
        routePostDTO.getWaypoints().forEach(waypoint -> {
            route.addWaypoint(new RouteWaypoint(waypoint.getLatitude(), waypoint.getLongitude()));
        });
        routePostDTO.getImages().forEach(image -> {
            route.addRouteImage(new RouteImage(image.getImage()));
        });
        routeRepository.save(route);
    }

    public void modifyRoute(RoutePostDTO routePostDTO, Long id) {
        if (routeRepository.existsById(id)) {
            routeRepository.findById(id).ifPresent(route -> {
                route.setDescription(routePostDTO.getDescription());
                route.setDuration(routePostDTO.getDuration());
                route.setDifficulty(routePostDTO.getDifficulty());
                route.setLatitude(routePostDTO.getLatitude());
                route.setLongitude(routePostDTO.getLongitude());
                route.setLength(routePostDTO.getLength());
                route.setName(routePostDTO.getName());
                route.setWeather(routePostDTO.getWeather());
                route.setSurface(routePostDTO.getSurface());
                route.setType(routePostDTO.getType());
                route.removeAllWaypoints();
                route.removeAllImages();
                routePostDTO.getWaypoints().forEach(waypoint -> {
                    route.addWaypoint(new RouteWaypoint(waypoint.getLatitude(), waypoint.getLongitude()));
                });
                routePostDTO.getImages().forEach(image -> {
                    route.addRouteImage(new RouteImage(image.getImage()));
                });
                routeRepository.save(route);
            });
        }
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
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
}
