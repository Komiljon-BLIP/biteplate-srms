package com.biteplate.biteplate_api.reservation.application.service;

import com.biteplate.biteplate_api.reservation.application.usecase.CancelReservationUseCase;
import com.biteplate.biteplate_api.reservation.application.usecase.CreateReservationUseCase;
import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.reservation.domain.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService
        implements
        CreateReservationUseCase,
        CancelReservationUseCase {

    private final ReservationRepository reservationRepository;

    public ReservationService(
            ReservationRepository reservationRepository
    ) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(
            Reservation reservation
    ) {

        return reservationRepository.save(
                reservation
        );

    }

    public Reservation confirmReservation(
            UUID reservationId
    ) {

        Reservation reservation =
                reservationRepository
                        .findById(
                                reservationId
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Reservation not found."
                                )
                        );

        reservation.confirm();

        return reservationRepository.save(
                reservation
        );

    }

    @Override
    public void cancelReservation(
            UUID reservationId
    ) {

        Reservation reservation =
                reservationRepository
                        .findById(
                                reservationId
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Reservation not found."
                                )
                        );

        reservation.cancel();

        reservationRepository.save(
                reservation
        );

    }

    public Reservation getReservationById(
            UUID reservationId
    ) {

        return reservationRepository
                .findById(
                        reservationId
                )
                .orElseThrow(
                        () -> new RuntimeException(
                                "Reservation not found."
                        )
                );

    }

}