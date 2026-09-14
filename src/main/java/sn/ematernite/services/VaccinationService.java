package sn.ematernite.services;

import sn.ematernite.model.request.VaccinationRequest;
import sn.ematernite.model.response.VaccinationResponse;

import java.util.List;

public interface VaccinationService {

    VaccinationResponse enregistrer(VaccinationRequest request);

    List<VaccinationResponse> getByCarnet(Long carnetId);

    VaccinationResponse getById(Long id);

    VaccinationResponse modifier(Long id, VaccinationRequest request);

    void supprimer(Long id);
}