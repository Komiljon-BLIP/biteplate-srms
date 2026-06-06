package com.biteplate.biteplate_api.infrastructure.concurrency;

import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ConcurrencyManager {

    private final ReservationQueue reservationQueue;

    public ConcurrencyManager(
            ReservationQueue reservationQueue
    ) {
        this.reservationQueue = reservationQueue;
    }

    public void submitReservation(
            Reservation reservation
    ) {

        try {

            ReservationTask task =
                    new ReservationTask(
                            reservation
                    );

            reservationQueue.submit(
                    task
            );

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

            throw new RuntimeException(
                    "Reservation submission interrupted.",
                    e
            );

        }

    }

    // ADD THIS METHOD HERE

    public int getQueueSize() {

        return reservationQueue.size();

    }

}