package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.FemmeEnceinteEntity;
import sn.ematernite.model.response.FemmeEnceinteResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FemmeEnceinteMapper
        extends EntityMapper<FemmeEnceinteResponse, FemmeEnceinteEntity> {
}