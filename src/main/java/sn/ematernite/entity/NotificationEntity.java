package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;
import sn.ematernite.entity.enums.TypeNotification;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class NotificationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "femme_enceinte_id")
    private FemmeEnceinteEntity femmeEnceinte;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeNotification type;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "message_wolof", columnDefinition = "TEXT")
    private String messageWolof;

    @Column(name = "est_lue")
    private Boolean estLue = false;

    @Column(name = "date_programmee")
    private LocalDateTime dateProgrammee;

    @Column(name = "date_envoi")
    private LocalDateTime dateEnvoi;
}