package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.model.response.CarnetResponse;
import sn.ematernite.model.response.ConsultationResponse;
import sn.ematernite.model.response.VaccinationResponse;
import sn.ematernite.repository.CarnetRepository;
import sn.ematernite.services.CarnetService;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarnetServiceImpl implements CarnetService {

    private final CarnetRepository carnetRepository;

    @Override
    public CarnetResponse getByGrossesse(Long grossesseId) {
        var carnet = carnetRepository.findByGrossesseId(grossesseId)
                .orElseThrow(() -> new RuntimeException("Carnet introuvable"));

        return CarnetResponse.builder()
                .id(carnet.getId())
                .statut(carnet.getStatut())
                .groupeSanguin(carnet.getGroupeSanguin())
                .antecedents(carnet.getAntecedents())
                .consultations(carnet.getConsultations().stream()
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
                                .build())
                        .collect(Collectors.toList()))
                .vaccinations(carnet.getVaccinations().stream()
                        .map(v -> VaccinationResponse.builder()
                                .id(v.getId())
                                .typeVaccin(v.getTypeVaccin())
                                .dateVaccin(v.getDateVaccin())
                                .centreVaccination(v.getCentreVaccination())
                                .observations(v.getObservations())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public CarnetResponse getByQRCode(String qrCode) {
        var carnet = carnetRepository.findByGrossesseFemmeEnceinteCodeQR(qrCode)
                .orElseThrow(() -> new RuntimeException("Carnet introuvable pour ce QR code"));

        return CarnetResponse.builder()
                .id(carnet.getId())
                .statut(carnet.getStatut())
                .groupeSanguin(carnet.getGroupeSanguin())
                .antecedents(carnet.getAntecedents())
                .consultations(carnet.getConsultations().stream()
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
                                .build())
                        .collect(Collectors.toList()))
                .vaccinations(carnet.getVaccinations().stream()
                        .map(v -> VaccinationResponse.builder()
                                .id(v.getId())
                                .typeVaccin(v.getTypeVaccin())
                                .dateVaccin(v.getDateVaccin())
                                .centreVaccination(v.getCentreVaccination())
                                .observations(v.getObservations())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}