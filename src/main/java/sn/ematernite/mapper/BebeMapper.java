package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.BebeEntity;
import sn.ematernite.model.response.BebeResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BebeMapper
        extends EntityMapper<BebeResponse, BebeEntity> {

    @Override
    @Mapping(target = "grossesseId", source = "grossesse.id")
    BebeResponse asDto(BebeEntity entity);
}