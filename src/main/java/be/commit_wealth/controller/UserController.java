package be.commit_wealth.controller;

import be.commit_wealth.dto.UserRegistrationRequest;
import be.commit_wealth.model.User;
import be.commit_wealth.repository.UserRepository;
import be.commit_wealth.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRegistrationRequest request) {
        User user = userService.registerUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


}
