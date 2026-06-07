package com.biteplate.biteplate_api.reservation.infrastructure.mapper;

import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.reservation.domain.valueobject.CustomerInfo;
import com.biteplate.biteplate_api.reservation.infrastructure.persistence.ReservationEntity;
import org.springframework.stereotype.Component;

@Component
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

    public Reservation toDomain(
            ReservationEntity entity
    ) {

        CustomerInfo customerInfo =
                new CustomerInfo(
                        entity.getCustomerName(),
                        entity.getPhoneNumber(),
                        entity.getEmail()
                );

        return new Reservation(
                entity.getId(),
                customerInfo,
                entity.getTableId(),
                entity.getTableType(),
                entity.getGuestCount(),
                null,
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );

    }

}