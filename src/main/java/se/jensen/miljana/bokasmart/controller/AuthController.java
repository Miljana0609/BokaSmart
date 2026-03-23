package se.jensen.miljana.bokasmart.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.jensen.miljana.bokasmart.dto.LoginDTO;
import se.jensen.miljana.bokasmart.dto.UserRequestDto;
import se.jensen.miljana.bokasmart.dto.UserResponseDto;
import se.jensen.miljana.bokasmart.model.User;
import se.jensen.miljana.bokasmart.service.AuthService;

/**
 * Controller responsible for authentication operations such as
 * user registration and login.
 * <p>
 * Provides endpoints for creating new users and generating JWT tokens.
 */

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // === Registrera ===
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRequestDto requestDto) {
        User user = authService.register(requestDto.getUsername(), requestDto.getPassword());
        return ResponseEntity.ok(new UserResponseDto(user.getUsername()));
    }

    // === Logga in ===
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(token);
    }


}
