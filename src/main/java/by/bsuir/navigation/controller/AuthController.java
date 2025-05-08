package by.bsuir.navigation.controller;

import by.bsuir.navigation.dto.auth.AuthDTO;
import by.bsuir.navigation.dto.auth.RegisterDTO;
import by.bsuir.navigation.dto.auth.TokenDTO;
import by.bsuir.navigation.security.JwtUtil;
import by.bsuir.navigation.service.impl.AuthService;
import by.bsuir.navigation.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return ResponseEntity.ok("Registered");
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody AuthDTO authDTO) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword()));
        return jwtUtil.generateToken(auth.getName(), auth.getAuthorities().toString());
    }
}

