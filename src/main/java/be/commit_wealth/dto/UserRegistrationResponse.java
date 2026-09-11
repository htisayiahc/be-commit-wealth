package be.commit_wealth.dto;

import be.commit_wealth.model.User;
import lombok.*;

import java.util.UUID;


@Value
@Builder
public class UserRegistrationResponse {
    UUID userId;

    String username;

    public static UserRegistrationResponse fromEntity(User user) {
        return UserRegistrationResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }
}
