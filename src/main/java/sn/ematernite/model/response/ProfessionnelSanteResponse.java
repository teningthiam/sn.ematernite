package sn.ematernite.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import sn.ematernite.entity.enums.Langue;
import sn.ematernite.entity.enums.Specialite;

import java.io.Serializable;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessionnelSanteResponse implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String matricule;
    private Specialite specialite;
    private Langue langue;
    private Boolean actif;
    private String nomStructure;
}