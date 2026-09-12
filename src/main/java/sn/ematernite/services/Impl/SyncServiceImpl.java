package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.model.request.SyncRequest;
import sn.ematernite.model.response.SyncResponse;
import sn.ematernite.services.ConsultationService;
import sn.ematernite.services.SyncService;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SyncServiceImpl implements SyncService {

    private final ConsultationService consultationService;

    @Override
    public SyncResponse traiterSync(SyncRequest request) {
        int traites = 0;
        int echecs = 0;

        // Traiter les consultations hors-ligne
        if (request.getConsultations() != null) {
            for (var consultation : request.getConsultations()) {
                try {
                    consultation.setStatutSync("SYNCHRONISE");
                    consultationService.enregistrer(consultation);
                    traites++;
                } catch (Exception e) {
                    log.error("Erreur sync consultation : {}", e.getMessage());
                    echecs++;
                }
            }
        }

        return SyncResponse.builder()
                .nombreTraites(traites)
                .nombreEchecs(echecs)
                .syncComplete(echecs == 0)
                .message(echecs == 0
                        ? "Synchronisation réussie"
                        : traites + " synchronisé(s), " + echecs + " échec(s)")
                .build();
    }

    @Override
    public SyncResponse getStatut(Long femmeEnceinteId) {
        return SyncResponse.builder()
                .syncComplete(true)
                .message("Données à jour")
                .build();
    }
}