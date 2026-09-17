package be.commit_wealth.repository;

import be.commit_wealth.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public class WalletRepository extends MongoRepository<Wallet, UUID> {
}
