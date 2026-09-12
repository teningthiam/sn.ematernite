package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.FemmeEnceinteEntity;
import sn.ematernite.entity.enums.Langue;
import sn.ematernite.entity.enums.Role;
import sn.ematernite.model.request.FemmeEnceinteRequest;
import sn.ematernite.model.response.FemmeEnceinteResponse;
import sn.ematernite.repository.FemmeEnceinteRepository;
import sn.ematernite.services.FemmeEnceinteService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FemmeEnceinteServiceImpl implements FemmeEnceinteService {

    private final FemmeEnceinteRepository femmeEnceinteRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public FemmeEnceinteResponse inscrire(FemmeEnceinteRequest request) {
        if (femmeEnceinteRepository.existsByTelephone(request.getTelephone())) {
            throw new RuntimeException(
                    "Ce numéro est déjà enregistré : " + request.getTelephone());
        }

        var femme = FemmeEnceinteEntity.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .telephone(request.getTelephone())
                .codePin(passwordEncoder.encode(
                        request.getCodePin() != null
                                ? request.getCodePin() : "000000"))
                .dateNaissance(request.getDateNaissance())
                .adresse(request.getAdresse())
                .langue(request.getLangue() != null
                        ? request.getLangue() : Langue.FRANCAIS)
                .role(Role.FEMME_ENCEINTE)
                .actif(true)
                // Génération automatique du QR code unique
                .codeQR(UUID.randomUUID().toString())
                .build();

        return toResponse(femmeEnceinteRepository.save(femme));
    }

    @Override
    public FemmeEnceinteResponse getById(Long id) {
        return toResponse(femmeEnceinteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Femme enceinte introuvable")));
    }

    @Override
    public FemmeEnceinteResponse getByTelephone(String telephone) {
        return toResponse(femmeEnceinteRepository
                .findByTelephone(telephone)
                .orElseThrow(() -> new RuntimeException(
                        "Femme enceinte introuvable")));
    }

    @Override
    public FemmeEnceinteResponse getByCodeQR(String codeQR) {
        return toResponse(femmeEnceinteRepository
                .findByCodeQR(codeQR)
                .orElseThrow(() -> new RuntimeException(
                        "Dossier introuvable pour ce QR code")));
    }

    @Override
    public List<FemmeEnceinteResponse> getAll() {
        return femmeEnceinteRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FemmeEnceinteResponse modifier(Long id, FemmeEnceinteRequest request) {
        var femme = femmeEnceinteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Femme enceinte introuvable"));

        femme.setNom(request.getNom());
        femme.setPrenom(request.getPrenom());
        femme.setAdresse(request.getAdresse());
        if (request.getLangue() != null) {
            femme.setLangue(request.getLangue());
        }

        return toResponse(femmeEnceinteRepository.save(femme));
    }

    private FemmeEnceinteResponse toResponse(FemmeEnceinteEntity f) {
        return FemmeEnceinteResponse.builder()
                .id(f.getId())
                .nom(f.getNom())
                .prenom(f.getPrenom())
                .telephone(f.getTelephone())
                .adresse(f.getAdresse())
                .codeQR(f.getCodeQR())
                .langue(f.getLangue())
                .actif(f.getActif())
                .build();
    }
}