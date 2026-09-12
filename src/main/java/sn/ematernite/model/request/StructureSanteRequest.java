package sn.ematernite.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StructureSanteRequest implements Serializable {

    @NotEmpty(message = "Le nom est obligatoire")
    private String nom;

    @NotEmpty(message = "La région est obligatoire")
    private String region;

    private String departement;
    private String typeStructure;
    private String telephone;
    private String adresse;
    private String codeDhis2;
}