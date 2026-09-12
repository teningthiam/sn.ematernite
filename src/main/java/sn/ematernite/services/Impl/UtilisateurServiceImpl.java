package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.UtilisateurEntity;
import sn.ematernite.model.request.UtilisateurRequest;
import sn.ematernite.model.response.UtilisateurResponse;
import sn.ematernite.repository.AdministrateurRepository;
import sn.ematernite.repository.FemmeEnceinteRepository;
import sn.ematernite.repository.ProfessionnelSanteRepository;
import sn.ematernite.services.UtilisateurService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final FemmeEnceinteRepository femmeEnceinteRepository;
    private final ProfessionnelSanteRepository professionnelRepository;
    private final AdministrateurRepository administrateurRepository;

    @Override
    public UtilisateurResponse creer(UtilisateurRequest request) {
        throw new UnsupportedOperationException(
                "Création via /api/auth ou /api/femmes ou /api/professionnels");
    }

    @Override
    public UtilisateurResponse getById(Long id) {
        // Chercher dans les 3 repositories
        UtilisateurEntity u = femmeEnceinteRepository.findById(id)
                .map(e -> (UtilisateurEntity) e)
                .orElseGet(() -> professionnelRepository.findById(id)
                        .map(e -> (UtilisateurEntity) e)
                        .orElseGet(() -> administrateurRepository.findById(id)
                                .map(e -> (UtilisateurEntity) e)
                                .orElseThrow(() -> new RuntimeException(
                                        "Utilisateur introuvable"))));
        return toResponse(u);
    }

    @Override
    public Page<UtilisateurResponse> getAll(
            Map<String, String> searchParams, Pageable pageable) {

        List<UtilisateurEntity> tous = new ArrayList<>();
        tous.addAll(femmeEnceinteRepository.findAll());
        tous.addAll(professionnelRepository.findAll());
        tous.addAll(administrateurRepository.findAll());

        List<UtilisateurResponse> responses = tous.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), responses.size());

        return new PageImpl<>(
                responses.subList(start, end),
                pageable,
                responses.size());
    }

    @Override
    public UtilisateurResponse modifier(
            Long id, UtilisateurRequest request) {
        return getById(id);
    }

    @Override
    public void activer(Long id) {
        femmeEnceinteRepository.findById(id).ifPresent(u -> {
            u.setActif(true);
            femmeEnceinteRepository.save(u);
        });
        professionnelRepository.findById(id).ifPresent(u -> {
            u.setActif(true);
            professionnelRepository.save(u);
        });
        administrateurRepository.findById(id).ifPresent(u -> {
            u.setActif(true);
            administrateurRepository.save(u);
        });
    }

    @Override
    public void desactiver(Long id) {
        femmeEnceinteRepository.findById(id).ifPresent(u -> {
            u.setActif(false);
            femmeEnceinteRepository.save(u);
        });
        professionnelRepository.findById(id).ifPresent(u -> {
            u.setActif(false);
            professionnelRepository.save(u);
        });
        administrateurRepository.findById(id).ifPresent(u -> {
            u.setActif(false);
            administrateurRepository.save(u);
        });
    }

    @Override
    public void supprimer(Long id) {
        if (femmeEnceinteRepository.existsById(id)) {
            femmeEnceinteRepository.deleteById(id);
        } else if (professionnelRepository.existsById(id)) {
            professionnelRepository.deleteById(id);
        } else if (administrateurRepository.existsById(id)) {
            administrateurRepository.deleteById(id);
        } else {
            throw new RuntimeException("Utilisateur introuvable");
        }
    }

    private UtilisateurResponse toResponse(UtilisateurEntity u) {
        return UtilisateurResponse.builder()
                .id(u.getId())
                .nom(u.getNom())
                .prenom(u.getPrenom())
                .telephone(u.getTelephone())
                .role(u.getRole())
                .langue(u.getLangue())
                .actif(u.getActif())
                .build();
    }
}