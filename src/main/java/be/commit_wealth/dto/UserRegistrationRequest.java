package be.commit_wealth.dto;

import be.commit_wealth.validation.RegistrationValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegistrationValidation
public class UserRegistrationRequest {

    private String username;

    private String password;

    private Double salary;

    private String birthdayDate;

    private String email;
}
