package sn.ematernite.services;

import sn.ematernite.model.request.ConseilRequest;
import sn.ematernite.model.response.ConseilResponse;

import java.util.List;

public interface ConseilService {

    ConseilResponse creer(ConseilRequest request);

    List<ConseilResponse> getBySemaine(int semaine, String langue);

    List<ConseilResponse> getAll();

    ConseilResponse getById(Long id);

    ConseilResponse modifier(Long id, ConseilRequest request);

    void supprimer(Long id);
}