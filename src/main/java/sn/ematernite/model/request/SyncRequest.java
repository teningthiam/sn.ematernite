package sn.ematernite.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncRequest implements Serializable {

    // Données collectées hors-ligne par Flutter (sqflite)
    private List<ConsultationRequest> consultations = new ArrayList<>();
    private List<VaccinationRequest> vaccinations = new ArrayList<>();
}