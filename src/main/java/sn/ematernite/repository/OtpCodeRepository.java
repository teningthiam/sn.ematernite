package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.OtpCodeEntity;

import java.util.Optional;

@Repository
public interface OtpCodeRepository
        extends JpaRepository<OtpCodeEntity, Long> {

    Optional<OtpCodeEntity> findByTelephoneAndEstUtiliseFalse(
            String telephone);

    @Modifying
    @Query("DELETE FROM OtpCodeEntity o " +
            "WHERE o.expireLe < CURRENT_TIMESTAMP")
    void supprimerExpires();
}