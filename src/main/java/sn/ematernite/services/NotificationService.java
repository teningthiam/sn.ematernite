package sn.ematernite.services;

import sn.ematernite.model.response.NotificationResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationResponse> getNonLues(Long femmeEnceinteId);

    void marquerLue(Long notificationId);

    long compterNonLues(Long femmeEnceinteId);

    void envoyerRappelRDV(Long femmeEnceinteId, String message, String messageWolof);
}