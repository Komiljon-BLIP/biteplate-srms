package com.biteplate.biteplate_api.reservation.api.request;

import com.biteplate.biteplate_api.reservation.domain.enums.TableType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateReservationRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    @NotNull
    private TableType tableType;

    @Min(1)
    @Max(12)
    private Integer guestCount;

    @NotNull
    private LocalDate reservationDate;

    @NotNull
    private LocalTime reservationTime;

    public CreateReservationRequest() {
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public TableType getTableType() {
        return tableType;
    }

    public Integer getGuestCount() {
        return guestCount;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public LocalTime getReservationTime() {
        return reservationTime;
    }
}