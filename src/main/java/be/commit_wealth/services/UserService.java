package be.commit_wealth.services;

import be.commit_wealth.constants.ConstantValue;
import be.commit_wealth.dto.UserRegistrationRequest;
import be.commit_wealth.dto.UserRegistrationResponse;
import be.commit_wealth.model.User;
import be.commit_wealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

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
        User savedUser = userRepository.save(user);

        return UserRegistrationResponse.fromEntity(savedUser);
    }
}