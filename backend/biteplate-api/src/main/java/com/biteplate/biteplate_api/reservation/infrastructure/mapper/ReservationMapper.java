package com.biteplate.biteplate_api.reservation.infrastructure.mapper;


import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.reservation.domain.valueobject.CustomerInfo;
import com.biteplate.biteplate_api.reservation.infrastructure.persistence.ReservationEntity;

public class ReservationMapper {

    public ReservationEntity toEntity(
            Reservation reservation
    ) {

        return ReservationEntity
                .builder()
                .id(
                        reservation.getId()
                )
                .customerName(
                        reservation
                                .getCustomerInfo()
                                .getFullName()
                )
                .phoneNumber(
                        reservation
                                .getCustomerInfo()
                                .getPhoneNumber()
                )
                .email(
                        reservation
                                .getCustomerInfo()
                                .getEmail()
                )
                .tableId(
                        reservation.getTableId()
                )
                .tableType(
                        reservation.getTableType()
                )
                .guestCount(
                        reservation.getGuestCount()
                )
                .status(
                        reservation.getStatus()
                )
                .createdAt(
                        reservation.getCreatedAt()
                )
                .updatedAt(
                        reservation.getUpdatedAt()
                )
                .build();

    }

}