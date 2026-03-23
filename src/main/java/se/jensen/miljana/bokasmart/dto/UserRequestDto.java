package se.jensen.miljana.bokasmart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO used for user registration requests.
 * Contains username and password with validation rules.
 */
public class UserRequestDto {
    @NotBlank(message = "Username cannot be empty")
    private String username;


    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;


    public UserRequestDto() {
    }

    public UserRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

}
