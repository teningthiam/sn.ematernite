package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.GrossesseRequest;
import sn.ematernite.model.response.GrossesseResponse;
import sn.ematernite.services.GrossesseService;

import java.util.List;

@RestController
@RequestMapping("/api/grossesses")
@RequiredArgsConstructor
public class GrossesseController {

    private final GrossesseService grossesseService;

    @PostMapping
    public ResponseEntity<GrossesseResponse> creer(
            @Valid @RequestBody GrossesseRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(grossesseService.creer(request));
    }

    @GetMapping("/en-cours/{femmeEnceinteId}")
    public ResponseEntity<GrossesseResponse> getEnCours(
            @PathVariable Long femmeEnceinteId) {
        return ResponseEntity.ok(grossesseService.getEnCours(femmeEnceinteId));
    }

    @GetMapping("/historique/{femmeEnceinteId}")
    public ResponseEntity<List<GrossesseResponse>> getHistorique(
            @PathVariable Long femmeEnceinteId) {
        return ResponseEntity.ok(grossesseService.getHistorique(femmeEnceinteId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrossesseResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(grossesseService.getById(id));
    }
}