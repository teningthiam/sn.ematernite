package sn.ematernite.services;

import sn.ematernite.model.request.ProfessionnelSanteRequest;
import sn.ematernite.model.response.ProfessionnelSanteResponse;

import java.util.List;

public interface ProfessionnelSanteService {

    ProfessionnelSanteResponse inscrire(ProfessionnelSanteRequest request);

    ProfessionnelSanteResponse getById(Long id);

    List<ProfessionnelSanteResponse> getAll();

    List<ProfessionnelSanteResponse> getByStructure(Long structureId);

    ProfessionnelSanteResponse modifier(Long id, ProfessionnelSanteRequest request);
}