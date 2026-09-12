package sn.ematernite.services;

import sn.ematernite.model.request.ConsultationRequest;
import sn.ematernite.model.response.ConsultationResponse;

import java.util.List;

public interface ConsultationService {

    ConsultationResponse enregistrer(ConsultationRequest request);

    List<ConsultationResponse> getByCarnet(Long carnetId);

    ConsultationResponse getById(Long id);
}