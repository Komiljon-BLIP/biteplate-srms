package com.biteplate.biteplate_api.infrastructure.config;

import com.biteplate.biteplate_api.infrastructure.concurrency.ReservationQueue;
import com.biteplate.biteplate_api.infrastructure.concurrency.ReservationWorker;
import com.biteplate.biteplate_api.reservation.application.service.ReservationService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ConcurrencyConfig {

    private static final int
            WORKER_COUNT = 10;

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
    public void startWorkers() {

        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        WORKER_COUNT
                );

        for (int i = 1;
             i <= WORKER_COUNT;
             i++) {

            ReservationWorker worker =
                    new ReservationWorker(
                            reservationQueue,
                            reservationService
                    );

            executorService.submit(
                    worker
            );

            System.out.println(
                    "Reservation Worker "
                            + i
                            + " Started..."
            );

        }

    }

}