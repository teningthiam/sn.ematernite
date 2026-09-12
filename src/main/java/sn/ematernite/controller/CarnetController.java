package sn.ematernite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ematernite.model.response.CarnetResponse;
import sn.ematernite.services.CarnetService;

@RestController
@RequestMapping("/api/carnets")
@RequiredArgsConstructor
public class CarnetController {

    private final CarnetService carnetService;

    @GetMapping("/grossesse/{grossesseId}")
    public ResponseEntity<CarnetResponse> getByGrossesse(
            @PathVariable Long grossesseId) {
        return ResponseEntity.ok(carnetService.getByGrossesse(grossesseId));
    }

    @GetMapping("/qr/{qrCode}")
    public ResponseEntity<CarnetResponse> getByQRCode(
            @PathVariable String qrCode) {
        return ResponseEntity.ok(carnetService.getByQRCode(qrCode));
    }
}