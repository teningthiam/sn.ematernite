package sn.ematernite.services;

import sn.ematernite.model.request.PinRequest;
import sn.ematernite.model.request.SendOtpRequest;
import sn.ematernite.model.request.VerifyOtpRequest;
import sn.ematernite.model.response.AuthResponse;

public interface AuthService {

    void envoyerOTP(SendOtpRequest request);

    AuthResponse verifierOTP(VerifyOtpRequest request);

    AuthResponse connexionParPin(PinRequest request);
}