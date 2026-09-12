package sn.ematernite.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import sn.ematernite.entity.enums.Langue;

import java.io.Serializable;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FemmeEnceinteResponse implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String codeQR;
    private Langue langue;
    private Boolean actif;
}