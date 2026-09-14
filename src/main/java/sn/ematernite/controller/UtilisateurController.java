package sn.ematernite.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.request.UtilisateurRequest;
import sn.ematernite.model.response.UtilisateurResponse;
import sn.ematernite.services.UtilisateurService;

import java.util.Map;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurResponse> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                utilisateurService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UtilisateurResponse>> getAll(
            @RequestParam Map<String, String> searchParams,
            Pageable pageable) {
        return ResponseEntity.ok(
                utilisateurService.getAll(searchParams, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurResponse> modifier(
            @PathVariable Long id,
            @Valid @RequestBody UtilisateurRequest request) {
        return ResponseEntity.ok(
                utilisateurService.modifier(id, request));
    }

    @PatchMapping("/{id}/activer")
    public ResponseEntity<Void> activer(@PathVariable Long id) {
        utilisateurService.activer(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/desactiver")
    public ResponseEntity<Void> desactiver(@PathVariable Long id) {
        utilisateurService.desactiver(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        utilisateurService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}