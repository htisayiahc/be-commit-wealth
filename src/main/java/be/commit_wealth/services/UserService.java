package be.commit_wealth.services;

import be.commit_wealth.dto.UserRegistrationRequest;
import be.commit_wealth.model.User;
import be.commit_wealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(UserRegistrationRequest request) {
        User user = new User(request);
        return userRepository.save(user);
    }
}