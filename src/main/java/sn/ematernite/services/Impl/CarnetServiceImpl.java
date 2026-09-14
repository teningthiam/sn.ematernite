package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.CarnetMaternelEntity;
import sn.ematernite.model.response.CarnetResponse;
import sn.ematernite.model.response.ConsultationResponse;
import sn.ematernite.model.response.VaccinationResponse;
import sn.ematernite.repository.CarnetRepository;
import sn.ematernite.services.CarnetService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CarnetServiceImpl implements CarnetService {

    private final CarnetRepository carnetRepository;

    @Override
    public CarnetResponse getByGrossesse(Long grossesseId) {
        log.info("Recherche carnet pour grossesse id : {}",
                grossesseId);

        var carnet = carnetRepository
                .findByGrossesseId(grossesseId)
                .orElseThrow(() -> new RuntimeException(
                        "Carnet introuvable pour la grossesse id : "
                                + grossesseId));

        log.info("Carnet trouvé id : {}", carnet.getId());

        return toResponse(carnet);
    }

    @Override
    public CarnetResponse getByQRCode(String qrCode) {
        log.info("Recherche carnet pour QR code : {}", qrCode);

        var carnet = carnetRepository
                .findByGrossesseFemmeEnceinteCodeQR(qrCode)
                .orElseThrow(() -> new RuntimeException(
                        "Carnet introuvable pour ce QR code"));

        return toResponse(carnet);
    }

    private CarnetResponse toResponse(CarnetMaternelEntity carnet) {

        List<ConsultationResponse> consultations = new ArrayList<>();
        if (carnet.getConsultations() != null) {
            consultations = carnet.getConsultations()
                    .stream()
                    .map(c -> ConsultationResponse.builder()
                            .id(c.getId())
                            .numeroCPN(c.getNumeroCPN())
                            .dateCPN(c.getDateCPN())
                            .semaineGrossesse(c.getSemaineGrossesse())
                            .poids(c.getPoids())
                            .tensionArterielle(c.getTensionArterielle())
                            .prochainRDV(c.getProchainRDV())
                            .notes(c.getNotes())
                            .statutSync(c.getStatutSync())
                            .nomStructure(
                                    c.getStructureSante() != null
                                            ? c.getStructureSante().getNom()
                                            : null)
                            .build())
                    .collect(Collectors.toList());
        }

        List<VaccinationResponse> vaccinations = new ArrayList<>();
        if (carnet.getVaccinations() != null) {
            vaccinations = carnet.getVaccinations()
                    .stream()
                    .map(v -> VaccinationResponse.builder()
                            .id(v.getId())
                            .typeVaccin(v.getTypeVaccin())
                            .dateVaccin(v.getDateVaccin())
                            .centreVaccination(v.getCentreVaccination())
                            .observations(v.getObservations())
                            .build())
                    .collect(Collectors.toList());
        }

        return CarnetResponse.builder()
                .id(carnet.getId())
                .statut(carnet.getStatut())
                .groupeSanguin(carnet.getGroupeSanguin())
                .antecedents(carnet.getAntecedents())
                .consultations(consultations)
                .vaccinations(vaccinations)
                .build();
    }
}