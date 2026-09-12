package sn.ematernite.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultationResponse implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Integer numeroCPN;
    private LocalDate dateCPN;
    private Integer semaineGrossesse;
    private Double poids;
    private String tensionArterielle;
    private LocalDate prochainRDV;
    private String notes;
    private String statutSync;
    private String nomStructure;
    private String nomProfessionnel;
}