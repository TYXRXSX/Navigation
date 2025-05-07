package by.bsuir.navigation.entity;

import by.bsuir.navigation.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

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
}
