package com.biteplate.biteplate_api.reservation.application.usecase;


import com.biteplate.biteplate_api.reservation.domain.model.Reservation;

public interface CreateReservationUseCase {

    Reservation createReservation(
            Reservation reservation
    );

}