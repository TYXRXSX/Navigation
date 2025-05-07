package by.bsuir.navigation.service.impl;

import by.bsuir.navigation.dto.auth.RegisterDTO;
import by.bsuir.navigation.entity.Users;
import by.bsuir.navigation.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
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

}
