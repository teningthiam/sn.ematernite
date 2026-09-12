package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.ProfessionnelSanteRequest;
import sn.ematernite.model.response.ProfessionnelSanteResponse;
import sn.ematernite.services.ProfessionnelSanteService;

import java.util.List;

@RestController
@RequestMapping("/api/professionnels")
@RequiredArgsConstructor
public class ProfessionnelSanteController {

    private final ProfessionnelSanteService professionnelService;

    @PostMapping
    public ResponseEntity<ProfessionnelSanteResponse> inscrire(
            @Valid @RequestBody ProfessionnelSanteRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professionnelService.inscrire(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionnelSanteResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(professionnelService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionnelSanteResponse>> getAll() {
        return ResponseEntity.ok(professionnelService.getAll());
    }

    @GetMapping("/structure/{structureId}")
    public ResponseEntity<List<ProfessionnelSanteResponse>> getByStructure(
            @PathVariable Long structureId) {
        return ResponseEntity.ok(
                professionnelService.getByStructure(structureId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionnelSanteResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody ProfessionnelSanteRequest request) {
        return ResponseEntity.ok(
                professionnelService.modifier(id, request));
    }
}