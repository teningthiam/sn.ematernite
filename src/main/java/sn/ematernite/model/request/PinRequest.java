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
public class PinRequest implements Serializable {

    @NotEmpty(message = "Le numéro de téléphone est obligatoire")
    private String telephone;

    @NotEmpty(message = "Le code PIN est obligatoire")
    private String codePin;
}