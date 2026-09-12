package sn.ematernite.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import sn.ematernite.entity.enums.StatutGrossesse;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GrossesseResponse implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dpa;
    private Integer numeroGrossesse;
    private StatutGrossesse statut;

    // Calculés automatiquement côté serveur
    private Integer semaineActuelle;
    private Long joursRestants;
    private Integer trimestre;
}