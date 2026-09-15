package be.commit_wealth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    @GetMapping("/me")
    public ResponseEntity<String> getWallets(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Getting wallets for user {}", userDetails.getUsername());
        return new ResponseEntity<>("Wallet", HttpStatus.CREATED);
    }
}
