package be.commit_wealth.repository;

import be.commit_wealth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    // Custom query method generated automatically by Spring Data
    Optional<User> findByEmail(String email);
}
