package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.FemmeEnceinteEntity;

import java.util.Optional;

@Repository
public interface FemmeEnceinteRepository
        extends JpaRepository<FemmeEnceinteEntity, Long> {

    Optional<FemmeEnceinteEntity> findByTelephone(String telephone);

    Optional<FemmeEnceinteEntity> findByCodeQR(String codeQR);

    boolean existsByTelephone(String telephone);
}