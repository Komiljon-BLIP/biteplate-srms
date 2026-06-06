package com.biteplate.biteplate_api.infrastructure.concurrency;

import com.biteplate.biteplate_api.reservation.application.service.ReservationService;

public class ReservationWorker
        implements Runnable {

    private final ReservationQueue queue;

    private final ReservationService reservationService;

    public ReservationWorker(
            ReservationQueue queue,
            ReservationService reservationService
    ) {
        this.queue = queue;
        this.reservationService = reservationService;
    }

    @Override
    public void run() {

        while (true) {

            try {

                ReservationTask task =
                        queue.take();

                reservationService
                        .createReservation(
                                task.getReservation()
                        );

            } catch (InterruptedException e) {

                Thread.currentThread()
                        .interrupt();

                break;

            }

        }

    }

}