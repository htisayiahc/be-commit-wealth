package be.commit_wealth.dto;

import lombok.*;

@Value
@Builder
public class LoginResponse {
    private String username;
    private String jwtToken;


}
