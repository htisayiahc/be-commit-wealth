package be.commit_wealth.services;

import be.commit_wealth.constants.ConstantValue;
import be.commit_wealth.model.CustomUserDetails;
import be.commit_wealth.model.User;
import be.commit_wealth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(ConstantValue.USERNAME_NOT_FOUND_ERROR_MESSAGE + username));

        return new CustomUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
