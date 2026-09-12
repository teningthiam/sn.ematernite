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
public class BebeRequest implements Serializable {

    @NotNull(message = "L'identifiant de la grossesse est obligatoire")
    private Long grossesseId;

    private String sexe;
    private Double poidsNaissance;
    private Double tailleNaissance;
    private LocalDate dateNaissance;
    private Integer apgarScore;
    private String lieuNaissance;
    private String observations;
}