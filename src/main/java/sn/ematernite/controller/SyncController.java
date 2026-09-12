package sn.ematernite.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.SyncRequest;
import sn.ematernite.model.response.SyncResponse;
import sn.ematernite.services.SyncService;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
@Slf4j
public class SyncController {

    private final SyncService syncService;

    @PostMapping("/upload")
    public ResponseEntity<SyncResponse> uploadDonneesHorsLigne(
            @RequestBody SyncRequest request) {
        log.info("Réception sync hors-ligne : {} consultations",
                request.getConsultations() != null
                        ? request.getConsultations().size() : 0);
        return ResponseEntity.ok(syncService.traiterSync(request));
    }

    @GetMapping("/statut/{femmeEnceinteId}")
    public ResponseEntity<SyncResponse> getStatut(
            @PathVariable Long femmeEnceinteId) {
        return ResponseEntity.ok(syncService.getStatut(femmeEnceinteId));
    }
}