package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.FemmeEnceinteRequest;
import sn.ematernite.model.response.FemmeEnceinteResponse;
import sn.ematernite.services.FemmeEnceinteService;

import java.util.List;

@RestController
@RequestMapping("/api/femmes")
@RequiredArgsConstructor
public class FemmeEnceinteController {

    private final FemmeEnceinteService femmeEnceinteService;

    @PostMapping
    public ResponseEntity<FemmeEnceinteResponse> inscrire(
            @Valid @RequestBody FemmeEnceinteRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(femmeEnceinteService.inscrire(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FemmeEnceinteResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(femmeEnceinteService.getById(id));
    }

    @GetMapping("/telephone/{telephone}")
    public ResponseEntity<FemmeEnceinteResponse> getByTelephone(
            @PathVariable String telephone) {
        return ResponseEntity.ok(
                femmeEnceinteService.getByTelephone(telephone));
    }

    @GetMapping("/qr/{codeQR}")
    public ResponseEntity<FemmeEnceinteResponse> getByCodeQR(
            @PathVariable String codeQR) {
        return ResponseEntity.ok(
                femmeEnceinteService.getByCodeQR(codeQR));
    }

    @GetMapping
    public ResponseEntity<List<FemmeEnceinteResponse>> getAll() {
        return ResponseEntity.ok(femmeEnceinteService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FemmeEnceinteResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody FemmeEnceinteRequest request) {
        return ResponseEntity.ok(
                femmeEnceinteService.modifier(id, request));
    }
}