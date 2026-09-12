package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.ProfessionnelSanteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionnelSanteRepository
        extends JpaRepository<ProfessionnelSanteEntity, Long> {

    Optional<ProfessionnelSanteEntity> findByTelephone(String telephone);

    Optional<ProfessionnelSanteEntity> findByMatricule(String matricule);

    List<ProfessionnelSanteEntity> findByStructureSanteId(Long structureId);

    boolean existsByMatricule(String matricule);
}