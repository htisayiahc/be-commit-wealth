package be.commit_wealth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    @PostMapping("/me")
    public ResponseEntity<String> getWallets(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Getting wallet for user {}", userDetails.getUsername());
        return new ResponseEntity<>("Wallet", HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity<?> getListWallets(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Getting wallets for user {}", userDetails.getUsername());
        return new ResponseEntity<>("Wallets", HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Creating wallet for user {}", userDetails.getUsername());
        return new ResponseEntity<>("Wallet", HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateWallet(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Updating wallet for user {}", userDetails.getUsername());
        return new ResponseEntity<>("Wallet", HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteWallet(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Deleting wallet for user {}", userDetails.getUsername());
    }
}
