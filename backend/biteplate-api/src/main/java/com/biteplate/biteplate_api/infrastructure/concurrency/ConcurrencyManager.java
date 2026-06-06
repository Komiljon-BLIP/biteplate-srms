package com.biteplate.biteplate_api.infrastructure.concurrency;

public class ConcurrencyManager {

    private final ReservationQueue reservationQueue;

    public ConcurrencyManager(
            ReservationQueue reservationQueue
    ) {
        this.reservationQueue = reservationQueue;
    }

    public void submitReservation(
            ReservationTask task
    ) throws InterruptedException {

        reservationQueue.submit(
                task
        );

    }

}