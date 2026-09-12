package sn.ematernite.services;

import sn.ematernite.model.request.GrossesseRequest;
import sn.ematernite.model.response.GrossesseResponse;

import java.util.List;

public interface GrossesseService {

    GrossesseResponse creer(GrossesseRequest request);

    GrossesseResponse getEnCours(Long femmeEnceinteId);

    List<GrossesseResponse> getHistorique(Long femmeEnceinteId);

    GrossesseResponse getById(Long id);
}