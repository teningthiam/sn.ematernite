package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.BebeEntity;
import sn.ematernite.model.request.BebeRequest;
import sn.ematernite.model.response.BebeResponse;
import sn.ematernite.repository.BebeRepository;
import sn.ematernite.repository.GrossesseRepository;
import sn.ematernite.services.BebeService;

@Service
@Transactional
@RequiredArgsConstructor
public class BebeServiceImpl implements BebeService {

    private final BebeRepository bebeRepository;
    private final GrossesseRepository grossesseRepository;

    @Override
    public BebeResponse enregistrer(BebeRequest request) {
        var grossesse = grossesseRepository.findById(request.getGrossesseId())
                .orElseThrow(() -> new RuntimeException("Grossesse introuvable"));

        var bebe = BebeEntity.builder()
                .grossesse(grossesse)
                .sexe(request.getSexe())
                .poidsNaissance(request.getPoidsNaissance())
                .tailleNaissance(request.getTailleNaissance())
                .dateNaissance(request.getDateNaissance())
                .apgarScore(request.getApgarScore())
                .lieuNaissance(request.getLieuNaissance())
                .observations(request.getObservations())
                .build();

        return toResponse(bebeRepository.save(bebe));
    }

    @Override
    public BebeResponse getById(Long id) {
        return toResponse(bebeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bébé introuvable")));
    }

    @Override
    public BebeResponse getByGrossesse(Long grossesseId) {
        return toResponse(bebeRepository.findByGrossesseId(grossesseId)
                .orElseThrow(() -> new RuntimeException("Bébé introuvable")));
    }

    @Override
    public BebeResponse modifier(Long id, BebeRequest request) {
        var bebe = bebeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bébé introuvable"));

        bebe.setSexe(request.getSexe());
        bebe.setPoidsNaissance(request.getPoidsNaissance());
        bebe.setTailleNaissance(request.getTailleNaissance());
        bebe.setDateNaissance(request.getDateNaissance());
        bebe.setApgarScore(request.getApgarScore());
        bebe.setLieuNaissance(request.getLieuNaissance());
        bebe.setObservations(request.getObservations());

        return toResponse(bebeRepository.save(bebe));
    }

    private BebeResponse toResponse(BebeEntity b) {
        return BebeResponse.builder()
                .id(b.getId())
                .sexe(b.getSexe())
                .poidsNaissance(b.getPoidsNaissance())
                .tailleNaissance(b.getTailleNaissance())
                .dateNaissance(b.getDateNaissance())
                .apgarScore(b.getApgarScore())
                .lieuNaissance(b.getLieuNaissance())
                .observations(b.getObservations())
                .grossesseId(b.getGrossesse().getId())
                .build();
    }
}