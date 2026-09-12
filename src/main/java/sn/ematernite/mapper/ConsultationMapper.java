package sn.ematernite.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import sn.ematernite.entity.ConsultationCPNEntity;
import sn.ematernite.model.response.ConsultationResponse;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ConsultationMapper
        extends EntityMapper<ConsultationResponse, ConsultationCPNEntity> {

    @Override
    @Mapping(target = "nomStructure", source = "structureSante.nom")
    @Mapping(target = "nomProfessionnel",
            expression = "java(entity.getProfessionnel() != null ? entity.getProfessionnel().getPrenom() + ' ' + entity.getProfessionnel().getNom() : null)")
    ConsultationResponse asDto(ConsultationCPNEntity entity);
}