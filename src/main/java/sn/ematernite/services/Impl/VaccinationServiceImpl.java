package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.VaccinationEntity;
import sn.ematernite.model.request.VaccinationRequest;
import sn.ematernite.model.response.VaccinationResponse;
import sn.ematernite.repository.CarnetRepository;
import sn.ematernite.repository.VaccinationRepository;
import sn.ematernite.services.VaccinationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final CarnetRepository carnetRepository;

    @Override
    public VaccinationResponse enregistrer(VaccinationRequest request) {
        var carnet = carnetRepository
                .findById(request.getCarnetId())
                .orElseThrow(() -> new RuntimeException(
                        "Carnet introuvable"));

        var vaccination = VaccinationEntity.builder()
                .carnet(carnet)
                .typeVaccin(request.getTypeVaccin())
                .dateVaccin(request.getDateVaccin())
                .centreVaccination(request.getCentreVaccination())
                .observations(request.getObservations())
                .build();

        return toResponse(vaccinationRepository.save(vaccination));
    }

    @Override
    public List<VaccinationResponse> getByCarnet(Long carnetId) {
        return vaccinationRepository
                .findByCarnetId(carnetId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VaccinationResponse getById(Long id) {
        return toResponse(vaccinationRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Vaccination introuvable")));
    }

    @Override
    public VaccinationResponse modifier(
            Long id, VaccinationRequest request) {
        var v = vaccinationRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Vaccination introuvable"));

        v.setTypeVaccin(request.getTypeVaccin());
        v.setDateVaccin(request.getDateVaccin());
        v.setCentreVaccination(request.getCentreVaccination());
        v.setObservations(request.getObservations());

        return toResponse(vaccinationRepository.save(v));
    }

    @Override
    public void supprimer(Long id) {
        if (!vaccinationRepository.existsById(id)) {
            throw new RuntimeException("Vaccination introuvable");
        }
        vaccinationRepository.deleteById(id);
    }

    private VaccinationResponse toResponse(VaccinationEntity v) {
        return VaccinationResponse.builder()
                .id(v.getId())
                .typeVaccin(v.getTypeVaccin())
                .dateVaccin(v.getDateVaccin())
                .centreVaccination(v.getCentreVaccination())
                .observations(v.getObservations())
                .build();
    }
}