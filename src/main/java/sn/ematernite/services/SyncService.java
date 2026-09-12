package sn.ematernite.services;

import sn.ematernite.model.request.SyncRequest;
import sn.ematernite.model.response.SyncResponse;

public interface SyncService {

    SyncResponse traiterSync(SyncRequest request);

    SyncResponse getStatut(Long femmeEnceinteId);
}