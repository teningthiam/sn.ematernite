package sn.ematernite.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "otp_codes")
public class OtpCodeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "expire_le", nullable = false)
    private LocalDateTime expireLe;

    @Column(name = "est_utilise")
    private Boolean estUtilise = false;
}