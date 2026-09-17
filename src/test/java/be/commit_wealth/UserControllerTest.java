package be.commit_wealth;

import be.commit_wealth.constants.ConstantValue;
import be.commit_wealth.controller.UserController;
import be.commit_wealth.dto.UserRegistrationRequest;
import be.commit_wealth.dto.UserRegistrationResponse;
import be.commit_wealth.model.User;
import be.commit_wealth.services.JWTService;
import be.commit_wealth.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = UserController.class
)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    UserService userService;

    @MockitoBean
    JWTService jwtService;

    @Test
    @DisplayName("Register a new user successfully")
    void registerSuccess() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("Test-01");
        request.setPassword("P@ssw0rd");
        request.setEmail("Test01@gmail.com");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("test");

        UserRegistrationResponse testResponse = UserRegistrationResponse.fromEntity(user);

        when(userService.registerUser(request)).thenReturn(testResponse);

        mockMvc.perform(
                        post("/api/v1/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Register a user with invalid username")
    void registerInvalidUsername() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("Invalid_User!@#");
        request.setPassword("P@ssw0rd");
        request.setEmail("test01@gmail.com");

        mockMvc.perform(
                        post("/api/v1/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Register a user with invalid password")
    void registerInvalidPassword() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("Test-01");
        request.setPassword("Invalid");
        request.setEmail("Test01@gmail.com");

        mockMvc.perform(
                        post("/api/v1/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Register a user with invalid email")
    void registerInvalidEmail() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("Test-01");
        request.setPassword("P@ssw0rd");
        request.setEmail("Invalid-email");

        mockMvc.perform(
                        post("/api/v1/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Register a user with invalid birthdayDate")
    void registerInvalidBirthdayDate() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("Test-01");
        request.setPassword("P@ssw0rd");
        request.setEmail("Test01@gmail.com");
        request.setBirthdayDate("2000-01-32T00:00:00"); // Invalid day

        mockMvc.perform(
                        post("/api/v1/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Register a user with existing username")
    void registerExistingUsername() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("ExistingUser");
        request.setPassword("P@ssw0rd");
        request.setEmail("test01@gmail.com");

        when(userService.registerUser(any(UserRegistrationRequest.class))).thenThrow(new RuntimeException(ConstantValue.USERNAME_EXISTS_ERROR_MESSAGE));

        mockMvc.perform(
                        post("/api/v1/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Register a user with existing email")
    void registerExistingEmail() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("Test-01");
        request.setPassword("P@ssw0rd");
        request.setEmail("existingEmail@example.com");

        when(userService.registerUser(any(UserRegistrationRequest.class))).thenThrow(new RuntimeException(ConstantValue.EMAIL_EXISTS_ERROR_MESSAGE));

        mockMvc.perform(
                        post("/api/v1/users/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }
}