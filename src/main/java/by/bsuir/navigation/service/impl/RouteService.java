package by.bsuir.navigation.service.impl;

import by.bsuir.navigation.dto.crud.route.RouteGetDTO;
import by.bsuir.navigation.dto.crud.route.RoutePostDTO;
import by.bsuir.navigation.repo.RouteRepository;
import by.bsuir.navigation.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService implements CrudService {

    private final RouteRepository routeRepository;

    public RouteGetDTO getRoute(Long id) {
        return null;
    }

    public List<RouteGetDTO> getAllRoutes() {
        return null;
    }

    public void saveRoute(RoutePostDTO routePostDTO) {

    }

    public void modifyRoute(RoutePostDTO routePostDTO, Long id) {

    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }


}
