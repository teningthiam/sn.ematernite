package sn.ematernite.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import sn.ematernite.entity.enums.StatutCarnet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarnetResponse implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private StatutCarnet statut;
    private String groupeSanguin;
    private String antecedents;

    private List<ConsultationResponse> consultations = new ArrayList<>();
    private List<VaccinationResponse> vaccinations = new ArrayList<>();
}