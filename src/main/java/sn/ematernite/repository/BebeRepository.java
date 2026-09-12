package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.BebeEntity;

import java.util.Optional;

@Repository
public interface BebeRepository extends JpaRepository<BebeEntity, Long> {

    Optional<BebeEntity> findByGrossesseId(Long grossesseId);
}