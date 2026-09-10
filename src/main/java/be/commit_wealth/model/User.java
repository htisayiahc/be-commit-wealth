package be.commit_wealth.model;

import be.commit_wealth.dto.UserRegistrationRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    private UUID id;

    private String username;

    private String password;

    private Double salary;

    private String email;

    private String birthdayDate;

    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    public User(UserRegistrationRequest userRegistrationRequest) {
        this.username = userRegistrationRequest.getUsername();
        this.email = userRegistrationRequest.getEmail();
        this.password = userRegistrationRequest.getPassword();
        this.salary = userRegistrationRequest.getSalary();
        this.birthdayDate = userRegistrationRequest.getBirthdayDate();
    }
}