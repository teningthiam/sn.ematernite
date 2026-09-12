package sn.ematernite.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VaccinationRequest implements Serializable {

    @NotNull(message = "L'identifiant du carnet est obligatoire")
    private Long carnetId;

    private String typeVaccin;
    private LocalDate dateVaccin;
    private String centreVaccination;
    private String observations;
}