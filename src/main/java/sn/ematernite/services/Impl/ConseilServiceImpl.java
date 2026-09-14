package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.ConseilSanteEntity;
import sn.ematernite.model.request.ConseilRequest;
import sn.ematernite.model.response.ConseilResponse;
import sn.ematernite.repository.ConseilRepository;
import sn.ematernite.services.ConseilService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ConseilServiceImpl implements ConseilService {

    private final ConseilRepository conseilRepository;

    @Override
    public ConseilResponse creer(ConseilRequest request) {
        var conseil = ConseilSanteEntity.builder()
                .semaineMin(request.getSemaineMin())
                .semaineMax(request.getSemaineMax())
                .titreFr(request.getTitreFr())
                .titreWolof(request.getTitreWolof())
                .contenuFr(request.getContenuFr())
                .contenuWolof(request.getContenuWolof())
                .urlAudioFr(request.getUrlAudioFr())
                .urlAudioWolof(request.getUrlAudioWolof())
                .categorie(request.getCategorie())
                .build();

        return toResponse(conseilRepository.save(conseil));
    }

    @Override
    public List<ConseilResponse> getBySemaine(
            int semaine, String langue) {
        return conseilRepository
                .findBySemaine(semaine)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConseilResponse> getAll() {
        return conseilRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConseilResponse getById(Long id) {
        return toResponse(conseilRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Conseil introuvable")));
    }

    @Override
    public ConseilResponse modifier(
            Long id, ConseilRequest request) {
        var conseil = conseilRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Conseil introuvable"));

        conseil.setSemaineMin(request.getSemaineMin());
        conseil.setSemaineMax(request.getSemaineMax());
        conseil.setTitreFr(request.getTitreFr());
        conseil.setTitreWolof(request.getTitreWolof());
        conseil.setContenuFr(request.getContenuFr());
        conseil.setContenuWolof(request.getContenuWolof());
        conseil.setUrlAudioFr(request.getUrlAudioFr());
        conseil.setUrlAudioWolof(request.getUrlAudioWolof());
        conseil.setCategorie(request.getCategorie());

        return toResponse(conseilRepository.save(conseil));
    }

    @Override
    public void supprimer(Long id) {
        if (!conseilRepository.existsById(id)) {
            throw new RuntimeException("Conseil introuvable");
        }
        conseilRepository.deleteById(id);
    }

    private ConseilResponse toResponse(ConseilSanteEntity c) {
        return ConseilResponse.builder()
                .id(c.getId())
                .semaineMin(c.getSemaineMin())
                .semaineMax(c.getSemaineMax())
                .titreFr(c.getTitreFr())
                .titreWolof(c.getTitreWolof())
                .contenuFr(c.getContenuFr())
                .contenuWolof(c.getContenuWolof())
                .urlAudioFr(c.getUrlAudioFr())
                .urlAudioWolof(c.getUrlAudioWolof())
                .categorie(c.getCategorie())
                .build();
    }
}