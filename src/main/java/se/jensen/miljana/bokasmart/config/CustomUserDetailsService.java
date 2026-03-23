package se.jensen.miljana.bokasmart.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.jensen.miljana.bokasmart.model.User;
import se.jensen.miljana.bokasmart.repository.UserRepository;

import java.util.List;

/**
 * Custom implementation of UserDetailsService.
 * Loads user-specific data from the database
 * for authentication purposes.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kunde inte hitta användare"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of()
        );
    }
}
