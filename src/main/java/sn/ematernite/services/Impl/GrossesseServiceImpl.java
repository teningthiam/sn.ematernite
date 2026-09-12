package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.GrossesseEntity;
import sn.ematernite.entity.enums.StatutGrossesse;
import sn.ematernite.model.request.GrossesseRequest;
import sn.ematernite.model.response.GrossesseResponse;
import sn.ematernite.repository.FemmeEnceinteRepository;
import sn.ematernite.repository.GrossesseRepository;
import sn.ematernite.services.GrossesseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GrossesseServiceImpl implements GrossesseService {

    private final GrossesseRepository grossesseRepository;
    private final FemmeEnceinteRepository femmeEnceinteRepository;

    @Override
    public GrossesseResponse creer(GrossesseRequest request) {
        var femme = femmeEnceinteRepository.findById(request.getFemmeEnceinteId())
                .orElseThrow(() -> new RuntimeException("Femme enceinte introuvable"));

        var grossesse = GrossesseEntity.builder()
                .femmeEnceinte(femme)
                .dateDebut(request.getDateDebut())
                .dpa(request.getDpa())
                .numeroGrossesse(request.getNumeroGrossesse() != null ? request.getNumeroGrossesse() : 1)
                .statut(StatutGrossesse.EN_COURS)
                .build();

        var saved = grossesseRepository.save(grossesse);
        return toResponse(saved);
    }

    @Override
    public GrossesseResponse getEnCours(Long femmeEnceinteId) {
        var grossesse = grossesseRepository
                .findByFemmeEnceinteIdAndStatut(femmeEnceinteId, StatutGrossesse.EN_COURS)
                .orElseThrow(() -> new RuntimeException("Aucune grossesse en cours"));
        return toResponse(grossesse);
    }

    @Override
    public List<GrossesseResponse> getHistorique(Long femmeEnceinteId) {
        return grossesseRepository.findByFemmeEnceinteId(femmeEnceinteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GrossesseResponse getById(Long id) {
        var grossesse = grossesseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grossesse introuvable"));
        return toResponse(grossesse);
    }

    private GrossesseResponse toResponse(GrossesseEntity g) {
        return GrossesseResponse.builder()
                .id(g.getId())
                .dateDebut(g.getDateDebut())
                .dpa(g.getDpa())
                .numeroGrossesse(g.getNumeroGrossesse())
                .statut(g.getStatut())
                .semaineActuelle(g.calculerSemaine())
                .joursRestants(g.getJoursRestants())
                .trimestre(g.calculerSemaine() <= 13 ? 1 : g.calculerSemaine() <= 26 ? 2 : 3)
                .build();
    }
}