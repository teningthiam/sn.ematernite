package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.ConseilRequest;
import sn.ematernite.model.response.ConseilResponse;
import sn.ematernite.services.ConseilService;

import java.util.List;

@RestController
@RequestMapping("/api/conseils")
@RequiredArgsConstructor
public class ConseilController {

    private final ConseilService conseilService;

    @PostMapping
    public ResponseEntity<ConseilResponse> creer(
            @Valid @RequestBody ConseilRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(conseilService.creer(request));
    }

    @GetMapping
    public ResponseEntity<List<ConseilResponse>> getBySemaine(
            @RequestParam int semaine,
            @RequestParam(defaultValue = "fr") String langue) {
        return ResponseEntity.ok(
                conseilService.getBySemaine(semaine, langue));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ConseilResponse>> getAll() {
        return ResponseEntity.ok(conseilService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConseilResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(conseilService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConseilResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody ConseilRequest request) {
        return ResponseEntity.ok(
                conseilService.modifier(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        conseilService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}