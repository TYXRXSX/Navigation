package by.bsuir.navigation.dto.crud.user;

import lombok.Data;

@Data
public class UserChangeDataDTO {
    private String name;
    private String email;
    private byte[] avatar;
    private String password;
}
