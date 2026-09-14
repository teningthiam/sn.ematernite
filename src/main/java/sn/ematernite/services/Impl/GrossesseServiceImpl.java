package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.CarnetMaternelEntity;
import sn.ematernite.entity.GrossesseEntity;
import sn.ematernite.entity.enums.StatutCarnet;
import sn.ematernite.entity.enums.StatutGrossesse;
import sn.ematernite.model.request.GrossesseRequest;
import sn.ematernite.model.response.GrossesseResponse;
import sn.ematernite.repository.CarnetRepository;
import sn.ematernite.repository.FemmeEnceinteRepository;
import sn.ematernite.repository.GrossesseRepository;
import sn.ematernite.services.GrossesseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GrossesseServiceImpl implements GrossesseService {

    private final GrossesseRepository grossesseRepository;
    private final FemmeEnceinteRepository femmeEnceinteRepository;
    private final CarnetRepository carnetRepository;

    @Override
    public GrossesseResponse creer(GrossesseRequest request) {
        var femme = femmeEnceinteRepository
                .findById(request.getFemmeEnceinteId())
                .orElseThrow(() -> new RuntimeException(
                        "Femme enceinte introuvable"));

        // 1. Créer et sauvegarder la grossesse d'abord
        var grossesse = GrossesseEntity.builder()
                .femmeEnceinte(femme)
                .dateDebut(request.getDateDebut())
                .dpa(request.getDpa())
                .numeroGrossesse(
                        request.getNumeroGrossesse() != null
                                ? request.getNumeroGrossesse() : 1)
                .statut(StatutGrossesse.EN_COURS)
                .build();

        // 2. Flush pour s'assurer que la grossesse est bien
        //    persistée avant de créer le carnet
        var savedGrossesse = grossesseRepository
                .saveAndFlush(grossesse);

        log.info("Grossesse créée avec id : {}", savedGrossesse.getId());

        // 3. Créer le carnet avec la grossesse déjà persistée
        var carnet = CarnetMaternelEntity.builder()
                .grossesse(savedGrossesse)
                .statut(StatutCarnet.EN_COURS)
                .build();

        var savedCarnet = carnetRepository.saveAndFlush(carnet);

        log.info("Carnet créé avec id : {} pour grossesse id : {}",
                savedCarnet.getId(), savedGrossesse.getId());

        return toResponse(savedGrossesse);
    }

    @Override
    public GrossesseResponse getEnCours(Long femmeEnceinteId) {
        var grossesse = grossesseRepository
                .findByFemmeEnceinteIdAndStatut(
                        femmeEnceinteId,
                        StatutGrossesse.EN_COURS)
                .orElseThrow(() -> new RuntimeException(
                        "Aucune grossesse en cours"));
        return toResponse(grossesse);
    }

    @Override
    public List<GrossesseResponse> getHistorique(Long femmeEnceinteId) {
        return grossesseRepository
                .findByFemmeEnceinteId(femmeEnceinteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GrossesseResponse getById(Long id) {
        var grossesse = grossesseRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Grossesse introuvable"));
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
                .trimestre(g.calculerSemaine() <= 13 ? 1
                        : g.calculerSemaine() <= 26 ? 2 : 3)
                .build();
    }
}