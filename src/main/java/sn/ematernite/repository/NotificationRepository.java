package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.NotificationEntity;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findByFemmeEnceinteIdAndEstLueFalse(Long femmeEnceinteId);

    List<NotificationEntity> findByFemmeEnceinteIdOrderByDateEnvoiDesc(Long femmeEnceinteId);

    long countByFemmeEnceinteIdAndEstLueFalse(Long femmeEnceinteId);
}