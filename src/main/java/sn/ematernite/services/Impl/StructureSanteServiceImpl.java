package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.StructureSanteEntity;
import sn.ematernite.model.request.StructureSanteRequest;
import sn.ematernite.model.response.StructureSanteResponse;
import sn.ematernite.repository.StructureSanteRepository;
import sn.ematernite.services.StructureSanteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StructureSanteServiceImpl implements StructureSanteService {

    private final StructureSanteRepository structureRepository;

    @Override
    public StructureSanteResponse creer(StructureSanteRequest request) {
        var structure = StructureSanteEntity.builder()
                .nom(request.getNom())
                .region(request.getRegion())
                .departement(request.getDepartement())
                .typeStructure(request.getTypeStructure())
                .telephone(request.getTelephone())
                .adresse(request.getAdresse())
                .codeDhis2(request.getCodeDhis2())
                .build();

        return toResponse(structureRepository.save(structure));
    }

    @Override
    public List<StructureSanteResponse> getAll() {
        return structureRepository.findAll()
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public StructureSanteResponse getById(Long id) {
        return toResponse(structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure introuvable")));
    }

    @Override
    public List<StructureSanteResponse> getByRegion(String region) {
        return structureRepository.findByRegion(region)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public StructureSanteResponse modifier(Long id, StructureSanteRequest request) {
        var structure = structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure introuvable"));

        structure.setNom(request.getNom());
        structure.setRegion(request.getRegion());
        structure.setDepartement(request.getDepartement());
        structure.setTypeStructure(request.getTypeStructure());
        structure.setTelephone(request.getTelephone());
        structure.setAdresse(request.getAdresse());
        structure.setCodeDhis2(request.getCodeDhis2());

        return toResponse(structureRepository.save(structure));
    }

    @Override
    public void supprimer(Long id) {
        if (!structureRepository.existsById(id)) {
            throw new RuntimeException("Structure introuvable");
        }
        structureRepository.deleteById(id);
    }

    private StructureSanteResponse toResponse(StructureSanteEntity s) {
        return StructureSanteResponse.builder()
                .id(s.getId())
                .nom(s.getNom())
                .region(s.getRegion())
                .departement(s.getDepartement())
                .typeStructure(s.getTypeStructure())
                .telephone(s.getTelephone())
                .adresse(s.getAdresse())
                .codeDhis2(s.getCodeDhis2())
                .build();
    }
}