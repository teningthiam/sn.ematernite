package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.ConsultationCPNEntity;
import sn.ematernite.model.request.ConsultationRequest;
import sn.ematernite.model.response.ConsultationResponse;
import sn.ematernite.repository.CarnetRepository;
import sn.ematernite.repository.ConsultationRepository;
import sn.ematernite.repository.GrossesseRepository;
import sn.ematernite.repository.ProfessionnelSanteRepository;
import sn.ematernite.repository.StructureSanteRepository;
import sn.ematernite.services.ConsultationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final CarnetRepository carnetRepository;
    private final GrossesseRepository grossesseRepository;
    private final ProfessionnelSanteRepository professionnelRepository;
    private final StructureSanteRepository structureRepository;

    @Override
    public ConsultationResponse enregistrer(ConsultationRequest request) {
        var carnet = carnetRepository.findById(request.getCarnetId())
                .orElseThrow(() -> new RuntimeException("Carnet introuvable"));

        var grossesse = grossesseRepository.findById(request.getGrossesseId())
                .orElseThrow(() -> new RuntimeException("Grossesse introuvable"));

        var consultation = ConsultationCPNEntity.builder()
                .carnet(carnet)
                .grossesse(grossesse)
                .numeroCPN(request.getNumeroCPN())
                .dateCPN(request.getDateCPN())
                .semaineGrossesse(request.getSemaineGrossesse())
                .poids(request.getPoids())
                .tensionArterielle(request.getTensionArterielle())
                .prochainRDV(request.getProchainRDV())
                .notes(request.getNotes())
                .statutSync(request.getStatutSync())
                .synchroDhis2(false)
                .build();

        if (request.getProfessionnelId() != null) {
            professionnelRepository.findById(request.getProfessionnelId())
                    .ifPresent(consultation::setProfessionnel);
        }

        if (request.getStructureSanteId() != null) {
            structureRepository.findById(request.getStructureSanteId())
                    .ifPresent(consultation::setStructureSante);
        }

        var saved = consultationRepository.save(consultation);
        return toResponse(saved);
    }

    @Override
    public List<ConsultationResponse> getByCarnet(Long carnetId) {
        return consultationRepository.findByCarnetIdOrderByDateCPNDesc(carnetId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultationResponse getById(Long id) {
        var consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation introuvable"));
        return toResponse(consultation);
    }

    private ConsultationResponse toResponse(ConsultationCPNEntity c) {
        return ConsultationResponse.builder()
                .id(c.getId())
                .numeroCPN(c.getNumeroCPN())
                .dateCPN(c.getDateCPN())
                .semaineGrossesse(c.getSemaineGrossesse())
                .poids(c.getPoids())
                .tensionArterielle(c.getTensionArterielle())
                .prochainRDV(c.getProchainRDV())
                .notes(c.getNotes())
                .statutSync(c.getStatutSync())
                .nomStructure(c.getStructureSante() != null ? c.getStructureSante().getNom() : null)
                .nomProfessionnel(c.getProfessionnel() != null ?
                        c.getProfessionnel().getPrenom() + " " + c.getProfessionnel().getNom() : null)
                .build();
    }
}