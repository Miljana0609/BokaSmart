package se.jensen.miljana.bokasmart.dto;

/**
 * DTO used for returning user data to the client.
 * Provides a simplified representation of user information.
 */

public class UserResponseDto {
    private String username;


    public UserResponseDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
