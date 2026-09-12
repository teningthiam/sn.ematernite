package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.AdministrateurEntity;

import java.util.Optional;

@Repository
public interface AdministrateurRepository
        extends JpaRepository<AdministrateurEntity, Long> {

    Optional<AdministrateurEntity> findByTelephone(String telephone);

    boolean existsByTelephone(String telephone);
}