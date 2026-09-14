package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.CarnetMaternelEntity;

import java.util.Optional;

@Repository
public interface CarnetRepository
        extends JpaRepository<CarnetMaternelEntity, Long> {

    @Query("SELECT c FROM CarnetMaternelEntity c " +
            "WHERE c.grossesse.id = :grossesseId")
    Optional<CarnetMaternelEntity> findByGrossesseId(
            @Param("grossesseId") Long grossesseId);

    @Query("SELECT c FROM CarnetMaternelEntity c " +
            "WHERE c.grossesse.femmeEnceinte.codeQR = :codeQR")
    Optional<CarnetMaternelEntity> findByGrossesseFemmeEnceinteCodeQR(
            @Param("codeQR") String codeQR);
}