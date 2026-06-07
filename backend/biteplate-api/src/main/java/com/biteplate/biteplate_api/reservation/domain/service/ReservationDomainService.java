package com.biteplate.biteplate_api.reservation.domain.service;

import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ReservationDomainService {

    private static final int MIN_GUESTS = 1;
    private static final int MAX_GUESTS = 12;

    private static final LocalTime OPENING_TIME =
            LocalTime.of(9, 0);

    private static final LocalTime CLOSING_TIME =
            LocalTime.of(23, 0);

    public boolean canCreateReservation(
            Reservation reservation
    ) {

        return isGuestCountValid(
                reservation
        )
                &&
                isBusinessHours(
                        reservation
                );

    }

    public boolean isGuestCountValid(
            Reservation reservation
    ) {

        Integer guestCount =
                reservation.getGuestCount();

        return guestCount != null
                &&
                guestCount >= MIN_GUESTS
                &&
                guestCount <= MAX_GUESTS;

    }

    public boolean isBusinessHours(
            Reservation reservation
    ) {

        if (reservation == null
                || reservation.getTimeSlot() == null
                || reservation.getTimeSlot()
                .getReservationTime() == null) {

            return false;

        }

        LocalTime reservationTime =
                reservation
                        .getTimeSlot()
                        .getReservationTime();

        return !reservationTime.isBefore(
                OPENING_TIME
        )
                &&
                !reservationTime.isAfter(
                        CLOSING_TIME
                );

    }

}