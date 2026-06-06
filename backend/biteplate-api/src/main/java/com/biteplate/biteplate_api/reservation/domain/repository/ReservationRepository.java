package com.biteplate.biteplate_api.reservation.domain.repository;


import com.biteplate.biteplate_api.reservation.domain.model.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(UUID reservationId);

    List<Reservation> findAll();

    boolean existsActiveReservation(
            Long tableId
    );

}