package sn.ematernite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ematernite.entity.StructureSanteEntity;

import java.util.List;

@Repository
public interface StructureSanteRepository extends JpaRepository<StructureSanteEntity, Long> {

    List<StructureSanteEntity> findByRegion(String region);

    List<StructureSanteEntity> findByTypeStructure(String typeStructure);

    boolean existsByCodeDhis2(String codeDhis2);
}