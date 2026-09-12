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
public class ConsultationRequest implements Serializable {

    @NotNull(message = "L'identifiant du carnet est obligatoire")
    private Long carnetId;

    @NotNull(message = "L'identifiant de la grossesse est obligatoire")
    private Long grossesseId;

    private Long professionnelId;
    private Long structureSanteId;
    private Integer numeroCPN;
    private LocalDate dateCPN;
    private Integer semaineGrossesse;
    private Double poids;
    private String tensionArterielle;
    private LocalDate prochainRDV;
    private String notes;

    // Champ pour le mode offline-first
    // Flutter envoie "EN_ATTENTE" si hors ligne, "SYNCHRONISE" si en ligne
    private String statutSync = "SYNCHRONISE";
}