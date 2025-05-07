package by.bsuir.navigation.service.impl;

import by.bsuir.navigation.entity.Users;
import by.bsuir.navigation.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository repo;

    private final PasswordEncoder passwordEncoder;

    public void register(String username, String password) {
        if (repo.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        Users user = new Users();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPasswordHash(), Collections.emptyList());
    }
}

