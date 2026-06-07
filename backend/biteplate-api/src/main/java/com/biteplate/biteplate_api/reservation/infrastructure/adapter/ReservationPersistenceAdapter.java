package com.biteplate.biteplate_api.reservation.infrastructure.adapter;

import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.reservation.domain.repository.ReservationRepository;
import com.biteplate.biteplate_api.reservation.infrastructure.mapper.ReservationMapper;
import com.biteplate.biteplate_api.reservation.infrastructure.persistence.ReservationJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ReservationPersistenceAdapter
        implements ReservationRepository {

    private final ReservationJpaRepository jpaRepository;

    private final ReservationMapper mapper;

    public ReservationPersistenceAdapter(
            ReservationJpaRepository jpaRepository,
            ReservationMapper mapper
    ) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Reservation save(
            Reservation reservation
    ) {

        jpaRepository.save(
                mapper.toEntity(
                        reservation
                )
        );

        return reservation;

    }

    @Override
    public Optional<Reservation> findById(
            UUID reservationId
    ) {

        return jpaRepository
                .findById(
                        reservationId
                )
                .map(
                        mapper::toDomain
                );

    }

    @Override
    public List<Reservation> findAll() {

        return jpaRepository
                .findAll()
                .stream()
                .map(
                        mapper::toDomain
                )
                .toList();

    }

    @Override
    public boolean existsActiveReservation(
            Long tableId
    ) {

        return false;

    }

}