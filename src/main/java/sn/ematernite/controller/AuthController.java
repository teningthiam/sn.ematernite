package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.PinRequest;
import sn.ematernite.model.request.SendOtpRequest;
import sn.ematernite.model.request.VerifyOtpRequest;
import sn.ematernite.model.response.AuthResponse;
import sn.ematernite.services.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/send-otp")
    public ResponseEntity<Void> envoyerOTP(
            @Valid @RequestBody SendOtpRequest request) {
        authService.envoyerOTP(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifierOTP(
            @Valid @RequestBody VerifyOtpRequest request) {
        return ResponseEntity.ok(authService.verifierOTP(request));
    }

    @PostMapping("/login-pin")
    public ResponseEntity<AuthResponse> connexionPin(
            @Valid @RequestBody PinRequest request) {
        return ResponseEntity.ok(authService.connexionParPin(request));
    }
}