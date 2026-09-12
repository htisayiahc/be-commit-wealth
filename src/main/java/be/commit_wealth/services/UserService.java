package be.commit_wealth.services;

import be.commit_wealth.constants.ConstantValue;
import be.commit_wealth.dto.LoginRequest;
import be.commit_wealth.dto.LoginResponse;
import be.commit_wealth.dto.UserRegistrationRequest;
import be.commit_wealth.dto.UserRegistrationResponse;
import be.commit_wealth.exception.InvalidCredentialsException;
import be.commit_wealth.model.User;
import be.commit_wealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException(ConstantValue.USERNAME_EXISTS_ERROR_MESSAGE);
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException(ConstantValue.EMAIL_EXISTS_ERROR_MESSAGE);
        }

        User user = new User(request);
        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return UserRegistrationResponse.fromEntity(savedUser);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> {
                    log.warn("Authentication failed: User '{}' not found", loginRequest.getUsername());
                    return new InvalidCredentialsException("Wrong Username or password");
                });

        boolean isCorrectPassword = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

        if(!isCorrectPassword) {
            log.warn("Wrong password");
            throw new InvalidCredentialsException("Wrong Username or password");
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(user.getUsername());
        loginResponse.setJwtToken("JWT Token");

        return loginResponse;
    }


}