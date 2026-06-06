package com.biteplate.biteplate_api.reservation.api.response;

import com.biteplate.biteplate_api.reservation.domain.enums.ReservationStatus;

import java.util.UUID;

public class ReservationResponse {

    private UUID reservationId;

    private ReservationStatus status;

    private String message;

    public ReservationResponse(
            UUID reservationId,
            ReservationStatus status,
            String message
    ) {
        this.reservationId = reservationId;
        this.status = status;
        this.message = message;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}