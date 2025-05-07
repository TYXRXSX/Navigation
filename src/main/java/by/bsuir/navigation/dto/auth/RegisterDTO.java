package by.bsuir.navigation.dto.auth;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String email;
    private String name;
    private String password;
    private byte[] avatar;
}
