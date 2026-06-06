package com.biteplate.biteplate_api.reservation.api.mapper;

import com.biteplate.biteplate_api.reservation.api.request.CreateReservationRequest;
import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.reservation.domain.valueobject.CustomerInfo;
import com.biteplate.biteplate_api.reservation.domain.valueobject.ReservationTimeSlot;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class ReservationApiMapper {

    public Reservation toDomain(
            CreateReservationRequest request
    ) {

        CustomerInfo customerInfo =
                new CustomerInfo(
                        request.getFullName(),
                        request.getPhoneNumber(),
                        request.getEmail()
                );

        ReservationTimeSlot timeSlot =
                new ReservationTimeSlot(
                        request.getReservationDate(),
                        request.getReservationTime(),
                        120
                );

        return new Reservation(
                UUID.randomUUID(),
                customerInfo,
                null,
                request.getTableType(),
                request.getGuestCount(),
                timeSlot
        );

    }

}