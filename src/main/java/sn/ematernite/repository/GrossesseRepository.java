package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.GrossesseEntity;
import sn.ematernite.entity.enums.StatutGrossesse;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrossesseRepository extends JpaRepository<GrossesseEntity, Long> {

    List<GrossesseEntity> findByFemmeEnceinteId(Long femmeEnceinteId);

    Optional<GrossesseEntity> findByFemmeEnceinteIdAndStatut(
            Long femmeEnceinteId, StatutGrossesse statut);

    @Query("SELECT g FROM GrossesseEntity g WHERE g.statut = 'EN_COURS'")
    List<GrossesseEntity> findAllEnCours();
}