package sn.ematernite.services;

import sn.ematernite.model.request.StructureSanteRequest;
import sn.ematernite.model.response.StructureSanteResponse;

import java.util.List;

public interface StructureSanteService {

    StructureSanteResponse creer(StructureSanteRequest request);

    List<StructureSanteResponse> getAll();

    StructureSanteResponse getById(Long id);

    List<StructureSanteResponse> getByRegion(String region);

    StructureSanteResponse modifier(Long id, StructureSanteRequest request);

    void supprimer(Long id);
}