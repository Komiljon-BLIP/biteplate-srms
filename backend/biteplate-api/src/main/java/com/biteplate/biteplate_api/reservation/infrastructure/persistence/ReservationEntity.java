package com.biteplate.biteplate_api.reservation.infrastructure.persistence;

import com.biteplate.biteplate_api.reservation.domain.enums.ReservationStatus;
import com.biteplate.biteplate_api.reservation.domain.enums.TableType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "reservations")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ReservationEntity {

    @Id
    private UUID id;

    private String customerName;

    private String phoneNumber;

    private String email;

    private Long tableId;

    @Enumerated(EnumType.STRING)
    private TableType tableType;

    private Integer guestCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
