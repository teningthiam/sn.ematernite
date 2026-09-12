package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.StructureSanteRequest;
import sn.ematernite.model.response.StructureSanteResponse;
import sn.ematernite.services.StructureSanteService;

import java.util.List;

@RestController
@RequestMapping("/api/structures")
@RequiredArgsConstructor
public class StructureSanteController {

    private final StructureSanteService structureSanteService;

    @PostMapping
    public ResponseEntity<StructureSanteResponse> creer(
            @Valid @RequestBody StructureSanteRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(structureSanteService.creer(request));
    }

    @GetMapping
    public ResponseEntity<List<StructureSanteResponse>> getAll() {
        return ResponseEntity.ok(structureSanteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StructureSanteResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(structureSanteService.getById(id));
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<StructureSanteResponse>> getByRegion(
            @PathVariable String region) {
        return ResponseEntity.ok(structureSanteService.getByRegion(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StructureSanteResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody StructureSanteRequest request) {
        return ResponseEntity.ok(structureSanteService.modifier(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        structureSanteService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}