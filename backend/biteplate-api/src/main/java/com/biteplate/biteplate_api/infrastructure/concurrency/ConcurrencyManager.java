package com.biteplate.biteplate_api.infrastructure.concurrency;

import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.reservation.domain.service.ReservationDomainService;
import com.biteplate.biteplate_api.shared.exception.InvalidReservationException;
import org.springframework.stereotype.Component;

@Component
public class ConcurrencyManager {

    private final ReservationQueue reservationQueue;

    private final ReservationDomainService reservationDomainService;

    public ConcurrencyManager(
            ReservationQueue reservationQueue,
            ReservationDomainService reservationDomainService
    ) {
        this.reservationQueue = reservationQueue;
        this.reservationDomainService = reservationDomainService;
    }

    public void submitReservation(
            Reservation reservation
    ) {

        if (!reservationDomainService
                .canCreateReservation(
                        reservation
                )) {

            throw new InvalidReservationException(
                    "Reservation request is invalid."
            );

        }

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

    public int getQueueSize() {

        return reservationQueue.size();

    }

}