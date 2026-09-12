package sn.ematernite.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ematernite.model.request.UtilisateurRequest;
import sn.ematernite.model.response.UtilisateurResponse;

import java.util.Map;

public interface UtilisateurService {

    UtilisateurResponse creer(UtilisateurRequest request);

    UtilisateurResponse getById(Long id);

    Page<UtilisateurResponse> getAll(
            Map<String, String> searchParams, Pageable pageable);

    UtilisateurResponse modifier(Long id, UtilisateurRequest request);

    void activer(Long id);

    void desactiver(Long id);

    void supprimer(Long id);
}