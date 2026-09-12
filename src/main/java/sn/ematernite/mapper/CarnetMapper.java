package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.CarnetMaternelEntity;
import sn.ematernite.model.response.CarnetResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ConsultationMapper.class, VaccinationMapper.class}
)
public interface CarnetMapper
        extends EntityMapper<CarnetResponse, CarnetMaternelEntity> {
}