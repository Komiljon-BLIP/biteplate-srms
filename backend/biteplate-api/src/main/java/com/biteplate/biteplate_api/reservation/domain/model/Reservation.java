package com.biteplate.biteplate_api.reservation.domain.model;

import com.biteplate.biteplate_api.reservation.domain.enums.ReservationStatus;
import com.biteplate.biteplate_api.reservation.domain.enums.TableType;
import com.biteplate.biteplate_api.reservation.domain.valueobject.CustomerInfo;
import com.biteplate.biteplate_api.reservation.domain.valueobject.ReservationTimeSlot;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private CustomerInfo customerInfo;

    private Long tableId;

    private TableType tableType;

    private Integer guestCount;

    private ReservationTimeSlot timeSlot;

    private ReservationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // For NEW reservations

    public Reservation(
            UUID id,
            CustomerInfo customerInfo,
            Long tableId,
            TableType tableType,
            Integer guestCount,
            ReservationTimeSlot timeSlot
    ) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.tableId = tableId;
        this.tableType = tableType;
        this.guestCount = guestCount;
        this.timeSlot = timeSlot;
        this.status = ReservationStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // For LOADING reservations from the database

    public Reservation(
            UUID id,
            CustomerInfo customerInfo,
            Long tableId,
            TableType tableType,
            Integer guestCount,
            ReservationTimeSlot timeSlot,
            ReservationStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.customerInfo = customerInfo;
        this.tableId = tableId;
        this.tableType = tableType;
        this.guestCount = guestCount;
        this.timeSlot = timeSlot;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public Long getTableId() {
        return tableId;
    }

    public TableType getTableType() {
        return tableType;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public ReservationTimeSlot getTimeSlot() {
        return timeSlot;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}