package sn.ematernite.services;

import sn.ematernite.model.request.BebeRequest;
import sn.ematernite.model.response.BebeResponse;

public interface BebeService {

    BebeResponse enregistrer(BebeRequest request);

    BebeResponse getById(Long id);

    BebeResponse getByGrossesse(Long grossesseId);

    BebeResponse modifier(Long id, BebeRequest request);
}