package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.StructureSanteEntity;
import sn.ematernite.model.response.StructureSanteResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface StructureSanteMapper
        extends EntityMapper<StructureSanteResponse, StructureSanteEntity> {
}