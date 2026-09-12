package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.ProfessionnelSanteEntity;
import sn.ematernite.entity.enums.Langue;
import sn.ematernite.entity.enums.Role;
import sn.ematernite.model.request.ProfessionnelSanteRequest;
import sn.ematernite.model.response.ProfessionnelSanteResponse;
import sn.ematernite.repository.ProfessionnelSanteRepository;
import sn.ematernite.repository.StructureSanteRepository;
import sn.ematernite.services.ProfessionnelSanteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfessionnelSanteServiceImpl
        implements ProfessionnelSanteService {

    private final ProfessionnelSanteRepository professionnelRepository;
    private final StructureSanteRepository structureRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfessionnelSanteResponse inscrire(
            ProfessionnelSanteRequest request) {

        var professionnel = ProfessionnelSanteEntity.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .telephone(request.getTelephone())
                .codePin(passwordEncoder.encode(
                        request.getCodePin() != null
                                ? request.getCodePin() : "000000"))
                .matricule(request.getMatricule())
                .specialite(request.getSpecialite())
                .langue(request.getLangue() != null
                        ? request.getLangue() : Langue.FRANCAIS)
                .role(Role.SAGE_FEMME)
                .actif(true)
                .build();

        if (request.getStructureSanteId() != null) {
            structureRepository
                    .findById(request.getStructureSanteId())
                    .ifPresent(professionnel::setStructureSante);
        }

        return toResponse(professionnelRepository.save(professionnel));
    }

    @Override
    public ProfessionnelSanteResponse getById(Long id) {
        return toResponse(professionnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Professionnel introuvable")));
    }

    @Override
    public List<ProfessionnelSanteResponse> getAll() {
        return professionnelRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfessionnelSanteResponse> getByStructure(Long structureId) {
        return professionnelRepository
                .findByStructureSanteId(structureId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessionnelSanteResponse modifier(
            Long id, ProfessionnelSanteRequest request) {

        var p = professionnelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Professionnel introuvable"));

        p.setNom(request.getNom());
        p.setPrenom(request.getPrenom());
        p.setMatricule(request.getMatricule());
        p.setSpecialite(request.getSpecialite());

        if (request.getStructureSanteId() != null) {
            structureRepository
                    .findById(request.getStructureSanteId())
                    .ifPresent(p::setStructureSante);
        }

        return toResponse(professionnelRepository.save(p));
    }

    private ProfessionnelSanteResponse toResponse(
            ProfessionnelSanteEntity p) {
        return ProfessionnelSanteResponse.builder()
                .id(p.getId())
                .nom(p.getNom())
                .prenom(p.getPrenom())
                .telephone(p.getTelephone())
                .matricule(p.getMatricule())
                .specialite(p.getSpecialite())
                .langue(p.getLangue())
                .actif(p.getActif())
                .nomStructure(p.getStructureSante() != null
                        ? p.getStructureSante().getNom() : null)
                .build();
    }
}