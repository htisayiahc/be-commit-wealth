package be.commit_wealth.services;

import be.commit_wealth.model.Wallet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WalletService {

    public Wallet getWallet(String id) {
        return new Wallet();
    }

    public List<Wallet> getWallets() {
        return new ArrayList<>();
    }

    public Wallet createWallet(Wallet wallet) {
        return null;
    }

    public List<Wallet> updateWallet(List<Wallet> wallets) {
        return new ArrayList<>();
    }

    public Wallet deleteWallet(String id) {
        return null;
    }
}
