package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.GrossesseEntity;
import sn.ematernite.model.response.GrossesseResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GrossesseMapper
        extends EntityMapper<GrossesseResponse, GrossesseEntity> {

    @Override
    @Mapping(target = "semaineActuelle",
            expression = "java(entity.calculerSemaine())")
    @Mapping(target = "joursRestants",
            expression = "java(entity.getJoursRestants())")
    @Mapping(target = "trimestre",
            expression = "java(entity.calculerSemaine() <= 13 ? 1 : entity.calculerSemaine() <= 26 ? 2 : 3)")
    GrossesseResponse asDto(GrossesseEntity entity);
}