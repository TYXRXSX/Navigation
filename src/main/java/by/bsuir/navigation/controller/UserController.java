package by.bsuir.navigation.controller;

import by.bsuir.navigation.dto.auth.RegisterDTO;
import by.bsuir.navigation.dto.crud.route.RouteGetDTO;
import by.bsuir.navigation.dto.crud.user.UserChangeDataDTO;
import by.bsuir.navigation.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/favorite")
    public List<RouteGetDTO> getFavoriteRoutes() {
        return userService.getFavoriteRoutes();
    }

    @PostMapping("/information/change")
    public void changePersonalData(@RequestBody UserChangeDataDTO userChangeDataDTO) {
        userService.changePersonalData(userChangeDataDTO);
    }

}
