package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.ConsultationCPNEntity;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<ConsultationCPNEntity, Long> {

    List<ConsultationCPNEntity> findByCarnetIdOrderByDateCPNDesc(Long carnetId);

    // Pour la synchronisation offline-first
    List<ConsultationCPNEntity> findByStatutSync(String statutSync);

    // Pour la synchronisation DHIS2
    List<ConsultationCPNEntity> findBySynchroDhis2False();

    long countByCarnetId(Long carnetId);
}