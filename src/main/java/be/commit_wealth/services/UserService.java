package be.commit_wealth.services;

import be.commit_wealth.constants.ConstantValue;
import be.commit_wealth.dto.LoginRequest;
import be.commit_wealth.dto.LoginResponse;
import be.commit_wealth.dto.UserRegistrationRequest;
import be.commit_wealth.dto.UserRegistrationResponse;
import be.commit_wealth.exception.InvalidCredentialsException;
import be.commit_wealth.model.CustomUserDetails;
import be.commit_wealth.model.User;
import be.commit_wealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final JWTService jwtService;

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

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails == null) {
            throw new InvalidCredentialsException(ConstantValue.USER_ERROR_MESSAGE);
        }

        String jwtToken = jwtService.generateToken(userDetails);

        LoginResponse response = LoginResponse.builder()
                .username(userDetails.getUsername())
                .jwtToken(jwtToken)
                .build();

        return response;
    }
}