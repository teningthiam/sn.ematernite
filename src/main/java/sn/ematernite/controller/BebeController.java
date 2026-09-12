package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.BebeRequest;
import sn.ematernite.model.response.BebeResponse;
import sn.ematernite.services.BebeService;

@RestController
@RequestMapping("/api/bebes")
@RequiredArgsConstructor
public class BebeController {

    private final BebeService bebeService;

    @PostMapping
    public ResponseEntity<BebeResponse> enregistrer(
            @Valid @RequestBody BebeRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bebeService.enregistrer(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bebeService.getById(id));
    }

    @GetMapping("/grossesse/{grossesseId}")
    public ResponseEntity<BebeResponse> getByGrossesse(
            @PathVariable Long grossesseId) {
        return ResponseEntity.ok(bebeService.getByGrossesse(grossesseId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BebeResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody BebeRequest request) {
        return ResponseEntity.ok(bebeService.modifier(id, request));
    }
}