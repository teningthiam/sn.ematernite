package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.ConseilSanteEntity;

import java.util.List;

@Repository
public interface ConseilRepository extends JpaRepository<ConseilSanteEntity, Long> {

    // Récupérer les conseils adaptés à une semaine de grossesse
    @Query("SELECT c FROM ConseilSanteEntity c WHERE :semaine BETWEEN c.semaineMin AND c.semaineMax")
    List<ConseilSanteEntity> findBySemaine(int semaine);

    List<ConseilSanteEntity> findByCategorie(String categorie);
}