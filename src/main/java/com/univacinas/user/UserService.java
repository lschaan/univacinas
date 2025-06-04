package com.univacinas.user;

import com.univacinas.error.NoAvailableNurseException;
import com.univacinas.error.UserAlreadyExistsException;
import com.univacinas.error.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(CreateUserRequest createUserRequest) {
        Optional<User> userOptional = userRepository.findByUsername(createUserRequest.getUsername());

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }

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

    public List<User> list() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public User update(Long userId, UpdateUserRequest updateUserRequest) {
        User user = findById(userId);

        if (updateUserRequest.getUsername() != null) user.setUsername(updateUserRequest.getUsername());
        if (updateUserRequest.getName() != null) user.setName(updateUserRequest.getName());
        if (updateUserRequest.getEmail() != null) user.setEmail(updateUserRequest.getEmail());
        if (updateUserRequest.getPassword() != null) user.setPassword(updateUserRequest.getPassword());
        if (updateUserRequest.getRole() != null) user.setRole(updateUserRequest.getRole());

        return userRepository.save(user);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    //TODO: tornar mais eficiente? Garantir que enfermeiro não possui agendamento no horário?
    public User findNurse() {
        return userRepository.findFirstByRole(UserRole.NURSE).orElseThrow(NoAvailableNurseException::new);
    }

}
