package sn.ematernite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.response.NotificationResponse;
import sn.ematernite.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{femmeEnceinteId}")
    public ResponseEntity<List<NotificationResponse>> getNonLues(
            @PathVariable Long femmeEnceinteId) {
        return ResponseEntity.ok(notificationService.getNonLues(femmeEnceinteId));
    }

    @PatchMapping("/{id}/lue")
    public ResponseEntity<Void> marquerLue(@PathVariable Long id) {
        notificationService.marquerLue(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{femmeEnceinteId}/count")
    public ResponseEntity<Long> compterNonLues(
            @PathVariable Long femmeEnceinteId) {
        return ResponseEntity.ok(notificationService.compterNonLues(femmeEnceinteId));
    }
}