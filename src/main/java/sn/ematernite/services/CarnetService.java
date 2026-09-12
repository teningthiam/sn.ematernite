package sn.ematernite.services;

import sn.ematernite.model.response.CarnetResponse;

public interface CarnetService {

    CarnetResponse getByGrossesse(Long grossesseId);

    CarnetResponse getByQRCode(String qrCode);
}