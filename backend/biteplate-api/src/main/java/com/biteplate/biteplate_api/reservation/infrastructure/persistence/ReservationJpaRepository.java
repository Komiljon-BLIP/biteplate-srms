package com.biteplate.biteplate_api.reservation.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservationJpaRepository
        extends JpaRepository<
                ReservationEntity,
                UUID
                > {

}