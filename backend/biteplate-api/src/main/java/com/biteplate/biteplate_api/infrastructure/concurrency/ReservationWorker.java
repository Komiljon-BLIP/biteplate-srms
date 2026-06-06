package com.biteplate.biteplate_api.infrastructure.concurrency;

import com.biteplate.biteplate_api.reservation.application.service.ReservationService;

public class ReservationWorker implements Runnable {

    private final ReservationQueue reservationQueue;

    private final ReservationService reservationService;

    public ReservationWorker(
            ReservationQueue reservationQueue,
            ReservationService reservationService
    ) {
        this.reservationQueue = reservationQueue;
        this.reservationService = reservationService;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                ReservationTask task =
                        reservationQueue.take();

                reservationService.createReservation(
                        task.getReservation()
                );

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

            }

        }

    }

}