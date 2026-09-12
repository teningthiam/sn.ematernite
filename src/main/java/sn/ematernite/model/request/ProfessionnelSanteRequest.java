package sn.ematernite.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import sn.ematernite.entity.enums.Langue;
import sn.ematernite.entity.enums.Specialite;

import java.io.Serializable;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessionnelSanteRequest implements Serializable {

    private String nom;
    private String prenom;
    private String telephone;
    private String codePin;
    private String matricule;
    private Specialite specialite;
    private Langue langue;
    private Long structureSanteId;
}