package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.VaccinationEntity;
import sn.ematernite.model.response.VaccinationResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface VaccinationMapper
        extends EntityMapper<VaccinationResponse, VaccinationEntity> {
}