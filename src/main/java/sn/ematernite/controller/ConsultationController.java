package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.ConsultationRequest;
import sn.ematernite.model.response.ConsultationResponse;
import sn.ematernite.services.ConsultationService;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<ConsultationResponse> enregistrer(
            @Valid @RequestBody ConsultationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(consultationService.enregistrer(request));
    }

    @GetMapping("/carnet/{carnetId}")
    public ResponseEntity<List<ConsultationResponse>> getByCarnet(
            @PathVariable Long carnetId) {
        return ResponseEntity.ok(consultationService.getByCarnet(carnetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(consultationService.getById(id));
    }
}