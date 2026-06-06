package com.biteplate.biteplate_api.infrastructure.concurrency;

import com.biteplate.biteplate_api.reservation.domain.model.Reservation;

public class ReservationTask {

    private final Reservation reservation;

    public ReservationTask(
            Reservation reservation
    ) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

}