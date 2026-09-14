package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.VaccinationRequest;
import sn.ematernite.model.response.VaccinationResponse;
import sn.ematernite.services.VaccinationService;

import java.util.List;

@RestController
@RequestMapping("/api/vaccinations")
@RequiredArgsConstructor
public class VaccinationController {

    private final VaccinationService vaccinationService;

    @PostMapping
    public ResponseEntity<VaccinationResponse> enregistrer(
            @Valid @RequestBody VaccinationRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vaccinationService.enregistrer(request));
    }

    @GetMapping("/carnet/{carnetId}")
    public ResponseEntity<List<VaccinationResponse>> getByCarnet(
            @PathVariable Long carnetId) {
        return ResponseEntity.ok(
                vaccinationService.getByCarnet(carnetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaccinationResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                vaccinationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VaccinationResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody VaccinationRequest request) {
        return ResponseEntity.ok(
                vaccinationService.modifier(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        vaccinationService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}