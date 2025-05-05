package by.bsuir.navigation.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
public class User {
    private String name;
    private String email;
    private String passwordHash;
    private Byte[] avatar;
}
