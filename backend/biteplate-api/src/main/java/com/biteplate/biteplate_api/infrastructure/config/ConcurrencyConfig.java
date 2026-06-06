package com.biteplate.biteplate_api.infrastructure.config;

import com.biteplate.biteplate_api.infrastructure.concurrency.ReservationQueue;
import com.biteplate.biteplate_api.infrastructure.concurrency.ReservationWorker;
import com.biteplate.biteplate_api.reservation.application.service.ReservationService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcurrencyConfig {

    private final ReservationQueue reservationQueue;

    private final ReservationService reservationService;

    public ConcurrencyConfig(
            ReservationQueue reservationQueue,
            ReservationService reservationService
    ) {
        this.reservationQueue = reservationQueue;
        this.reservationService = reservationService;
    }

    @PostConstruct
    public void startWorker() {

        ReservationWorker worker =
                new ReservationWorker(
                        reservationQueue,
                        reservationService
                );

        Thread workerThread =
                new Thread(
                        worker
                );

        workerThread.setDaemon(
                true
        );

        workerThread.setName(
                "reservation-worker"
        );

        workerThread.start();
        System.out.println(
                "Reservation Worker Started..."
        );

    }

}