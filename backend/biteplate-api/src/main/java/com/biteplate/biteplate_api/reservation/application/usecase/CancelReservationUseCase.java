package com.biteplate.biteplate_api.reservation.application.usecase;


import java.util.UUID;

public interface CancelReservationUseCase {

    void cancelReservation(
            UUID reservationId
    );

}