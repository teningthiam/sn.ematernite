package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.CarnetMaternelEntity;

import java.util.Optional;

@Repository
public interface CarnetRepository extends JpaRepository<CarnetMaternelEntity, Long> {

    Optional<CarnetMaternelEntity> findByGrossesseId(Long grossesseId);

    Optional<CarnetMaternelEntity> findByGrossesseFemmeEnceinteCodeQR(String codeQR);
}