package se.jensen.miljana.bokasmart.service;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.jensen.miljana.bokasmart.config.JwtService;
import se.jensen.miljana.bokasmart.dto.LoginDTO;
import se.jensen.miljana.bokasmart.model.User;
import se.jensen.miljana.bokasmart.repository.UserRepository;

import java.util.NoSuchElementException;

/**
 * Service handling authentication logic.
 * Responsible for user registration, login authentication,
 * and retrieving user information.
 */

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    //=== Registrera ny användare ===
    public User register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER"); // default role

        return userRepository.save(user);
    }

    //=== Hämta user efter username
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found!"));
    }

    public String login(LoginDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow();
        return jwtService.generateToken(user);
    }
}
