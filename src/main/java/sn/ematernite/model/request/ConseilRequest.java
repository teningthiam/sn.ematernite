package sn.ematernite.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConseilRequest implements Serializable {

    private Integer semaineMin;
    private Integer semaineMax;
    private String titreFr;
    private String titreWolof;
    private String contenuFr;
    private String contenuWolof;
    private String urlAudioFr;
    private String urlAudioWolof;
    private String categorie;
}