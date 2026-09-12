package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.ProfessionnelSanteEntity;
import sn.ematernite.model.response.ProfessionnelSanteResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProfessionnelSanteMapper
        extends EntityMapper<ProfessionnelSanteResponse, ProfessionnelSanteEntity> {

    @Override
    @Mapping(target = "nomStructure", source = "structureSante.nom")
    ProfessionnelSanteResponse asDto(ProfessionnelSanteEntity entity);
}