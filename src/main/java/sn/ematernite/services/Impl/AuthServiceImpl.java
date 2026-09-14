package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.OtpCodeEntity;
import sn.ematernite.entity.UtilisateurEntity;
import sn.ematernite.model.request.PinRequest;
import sn.ematernite.model.request.SendOtpRequest;
import sn.ematernite.model.request.VerifyOtpRequest;
import sn.ematernite.model.response.AuthResponse;
import sn.ematernite.repository.AdministrateurRepository;
import sn.ematernite.repository.FemmeEnceinteRepository;
import sn.ematernite.repository.OtpCodeRepository;
import sn.ematernite.repository.ProfessionnelSanteRepository;
import sn.ematernite.services.AuthService;
import sn.ematernite.services.JwtService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final OtpCodeRepository otpCodeRepository;
    private final FemmeEnceinteRepository femmeEnceinteRepository;
    private final ProfessionnelSanteRepository professionnelRepository;
    private final AdministrateurRepository administrateurRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void envoyerOTP(SendOtpRequest request) {
        otpCodeRepository
                .findByTelephoneAndEstUtiliseFalse(request.getTelephone())
                .ifPresent(otpCodeRepository::delete);

        String code = String.format("%06d",
                new Random().nextInt(999999));

        OtpCodeEntity otpCode = OtpCodeEntity.builder()
                .telephone(request.getTelephone())
                .code(passwordEncoder.encode(code))
                .expireLe(LocalDateTime.now().plusMinutes(30))
                .estUtilise(false)
                .build();

        otpCodeRepository.save(otpCode);
        log.info("=== OTP généré pour {} : {} ===",
                request.getTelephone(), code);
    }

    @Override
    public AuthResponse verifierOTP(VerifyOtpRequest request) {
        log.info("Vérification OTP pour : {}", request.getTelephone());

        OtpCodeEntity otpCode = otpCodeRepository
                .findByTelephoneAndEstUtiliseFalse(request.getTelephone())
                .orElseThrow(() -> new RuntimeException(
                        "Code OTP introuvable"));

        if (otpCode.getExpireLe().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Code OTP expiré");
        }

        if (!passwordEncoder.matches(
                request.getCode(), otpCode.getCode())) {
            throw new RuntimeException("Code OTP invalide");
        }

        otpCode.setEstUtilise(true);
        otpCodeRepository.save(otpCode);

        log.info("OTP valide — recherche utilisateur...");

        UtilisateurEntity utilisateur =
                trouverParTelephone(request.getTelephone());

        log.info("Utilisateur trouvé : {} {}",
                utilisateur.getNom(), utilisateur.getPrenom());

        log.info("Génération du token JWT...");

        String token = jwtService.generateToken(utilisateur);

        log.info("Token généré avec succès : {}",
                token.substring(0, 20) + "...");

        return AuthResponse.builder()
                .token(token)
                .role(utilisateur.getRole().name())
                .telephone(utilisateur.getTelephone())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .build();
    }

    @Override
    public AuthResponse connexionParPin(PinRequest request) {
        log.info("Connexion PIN pour : {}", request.getTelephone());

        UtilisateurEntity utilisateur =
                trouverParTelephone(request.getTelephone());

        if (!passwordEncoder.matches(
                request.getCodePin(), utilisateur.getCodePin())) {
            throw new RuntimeException("Code PIN incorrect");
        }

        String token = jwtService.generateToken(utilisateur);

        return AuthResponse.builder()
                .token(token)
                .role(utilisateur.getRole().name())
                .telephone(utilisateur.getTelephone())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .build();
    }

    private UtilisateurEntity trouverParTelephone(String telephone) {
        Optional<? extends UtilisateurEntity> trouve =
                femmeEnceinteRepository.findByTelephone(telephone);

        if (trouve.isEmpty()) {
            trouve = professionnelRepository
                    .findByTelephone(telephone);
        }

        if (trouve.isEmpty()) {
            trouve = administrateurRepository
                    .findByTelephone(telephone);
        }

        return trouve.orElseThrow(() -> new RuntimeException(
                "Utilisateur introuvable : " + telephone));
    }
}