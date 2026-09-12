package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.VaccinationEntity;

import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {

    List<VaccinationEntity> findByCarnetId(Long carnetId);
}