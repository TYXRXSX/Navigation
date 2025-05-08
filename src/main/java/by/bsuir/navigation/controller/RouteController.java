package by.bsuir.navigation.controller;

import by.bsuir.navigation.dto.auth.RegisterDTO;
import by.bsuir.navigation.dto.crud.route.RouteGetDTO;
import by.bsuir.navigation.dto.crud.route.RoutePostDTO;
import by.bsuir.navigation.service.impl.RouteService;
import by.bsuir.navigation.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;
    private final UserService userService;

    @GetMapping("/")
    public List<RouteGetDTO> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public RouteGetDTO getRoute(@PathVariable Long id) {
        return routeService.getRoute(id);
    }

    @PostMapping("/")
    public void createRoute(@RequestBody RoutePostDTO routePostDTO) {
        routeService.saveRoute(routePostDTO);
    }

    @PutMapping("/{id}")
    public void putRoute(@RequestBody RoutePostDTO routePostDTO, @PathVariable Long id) {
        routeService.modifyRoute(routePostDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
    }

    @PostMapping("/{id}/favorite/add")
    public void addToFavorite(@PathVariable Long id) {
        userService.addToFavorite(id);
    }

    @PostMapping("/{id}/favorite/remove")
    public void removeFromFavorite(@PathVariable Long id) {
        userService.removeFromFavorite(id);
    }
}
