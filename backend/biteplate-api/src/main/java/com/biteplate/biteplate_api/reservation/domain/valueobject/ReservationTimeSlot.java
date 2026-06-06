package com.biteplate.biteplate_api.reservation.domain.valueobject;


import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationTimeSlot {

    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private int durationInMinutes;

    public ReservationTimeSlot(
            LocalDate reservationDate,
            LocalTime reservationTime,
            int durationInMinutes
    ) {
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.durationInMinutes = durationInMinutes;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

}