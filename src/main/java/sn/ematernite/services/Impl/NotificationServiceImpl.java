package sn.ematernite.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.ematernite.entity.NotificationEntity;
import sn.ematernite.entity.enums.TypeNotification;
import sn.ematernite.model.response.NotificationResponse;
import sn.ematernite.repository.FemmeEnceinteRepository;
import sn.ematernite.repository.NotificationRepository;
import sn.ematernite.services.NotificationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final FemmeEnceinteRepository femmeEnceinteRepository;

    @Override
    public List<NotificationResponse> getNonLues(Long femmeEnceinteId) {
        return notificationRepository
                .findByFemmeEnceinteIdAndEstLueFalse(femmeEnceinteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void marquerLue(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(n -> {
            n.setEstLue(true);
            notificationRepository.save(n);
        });
    }

    @Override
    public long compterNonLues(Long femmeEnceinteId) {
        return notificationRepository.countByFemmeEnceinteIdAndEstLueFalse(femmeEnceinteId);
    }

    @Override
    public void envoyerRappelRDV(Long femmeEnceinteId, String message, String messageWolof) {
        var femme = femmeEnceinteRepository.findById(femmeEnceinteId)
                .orElseThrow(() -> new RuntimeException("Femme enceinte introuvable"));

        var notification = NotificationEntity.builder()
                .femmeEnceinte(femme)
                .type(TypeNotification.RAPPEL_RDV)
                .message(message)
                .messageWolof(messageWolof)
                .estLue(false)
                .dateEnvoi(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }

    private NotificationResponse toResponse(NotificationEntity n) {
        return NotificationResponse.builder()
                .id(n.getId())
                .type(n.getType())
                .message(n.getMessage())
                .messageWolof(n.getMessageWolof())
                .estLue(n.getEstLue())
                .dateEnvoi(n.getDateEnvoi())
                .build();
    }
}