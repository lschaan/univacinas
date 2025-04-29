package com.univacinas.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(CreateUserRequest createUserRequest) {
        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());

        User user = User.builder()
            .username(createUserRequest.getUsername())
            .name(createUserRequest.getName())
            .email(createUserRequest.getEmail())
            .password(encodedPassword)
            .role(createUserRequest.getRole())
            .build();

        return userRepository.save(user);
    }
}
