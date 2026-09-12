package sn.ematernite.services;

import sn.ematernite.model.request.FemmeEnceinteRequest;
import sn.ematernite.model.response.FemmeEnceinteResponse;

import java.util.List;

public interface FemmeEnceinteService {

    FemmeEnceinteResponse inscrire(FemmeEnceinteRequest request);

    FemmeEnceinteResponse getById(Long id);

    FemmeEnceinteResponse getByTelephone(String telephone);

    FemmeEnceinteResponse getByCodeQR(String codeQR);

    List<FemmeEnceinteResponse> getAll();

    FemmeEnceinteResponse modifier(Long id, FemmeEnceinteRequest request);
}