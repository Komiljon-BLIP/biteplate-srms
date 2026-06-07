package com.biteplate.biteplate_api.reservation.api.controller;

import com.biteplate.biteplate_api.infrastructure.concurrency.ConcurrencyManager;
import com.biteplate.biteplate_api.reservation.api.mapper.ReservationApiMapper;
import com.biteplate.biteplate_api.reservation.api.request.CreateReservationRequest;
import com.biteplate.biteplate_api.reservation.api.response.ReservationResponse;
import com.biteplate.biteplate_api.reservation.application.service.ReservationService;
import com.biteplate.biteplate_api.reservation.domain.enums.ReservationStatus;
import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationApiMapper apiMapper;

    private final ConcurrencyManager concurrencyManager;

    private final ReservationService reservationService;

    public ReservationController(
            ReservationApiMapper apiMapper,
            ConcurrencyManager concurrencyManager,
            ReservationService reservationService
    ) {
        this.apiMapper = apiMapper;
        this.concurrencyManager = concurrencyManager;
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReservationResponse>>
    createReservation(
            @Valid
            @RequestBody
            CreateReservationRequest request
    ) {

        Reservation reservation =
                apiMapper.toDomain(
                        request
                );

        concurrencyManager.submitReservation(
                reservation
        );

        ReservationResponse response =
                new ReservationResponse(
                        reservation.getId(),
                        reservation.getStatus(),
                        "Reservation request accepted and added to processing queue."
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        response,
                        "Reservation request accepted and added to processing queue."
                )
        );

    }

    @GetMapping("/queue/status")
    public ResponseEntity<ApiResponse<Integer>>
    getQueueStatus() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        concurrencyManager.getQueueSize(),
                        "Current queue size."
                )
        );

    }

    @GetMapping("/queue/details")
    public ResponseEntity<ApiResponse<String>>
    getQueueDetails() {

        String message =
                "Current queue size: "
                        + concurrencyManager.getQueueSize();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        message,
                        "Queue details retrieved successfully."
                )
        );

    }

    @PostMapping("/{reservationId}/confirm")
    public ResponseEntity<ApiResponse<ReservationResponse>>
    confirmReservation(
            @PathVariable
            UUID reservationId
    ) {

        Reservation reservation =
                reservationService
                        .confirmReservation(
                                reservationId
                        );

        ReservationResponse response =
                new ReservationResponse(
                        reservation.getId(),
                        reservation.getStatus(),
                        "Reservation confirmed successfully."
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        response,
                        "Reservation confirmed successfully."
                )
        );

    }

    @PostMapping("/{reservationId}/cancel")
    public ResponseEntity<ApiResponse<ReservationResponse>>
    cancelReservation(
            @PathVariable
            UUID reservationId
    ) {

        reservationService.cancelReservation(
                reservationId
        );

        ReservationResponse response =
                new ReservationResponse(
                        reservationId,
                        ReservationStatus.CANCELLED,
                        "Reservation cancelled successfully."
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        response,
                        "Reservation cancelled successfully."
                )
        );

    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<ApiResponse<ReservationResponse>>
    getReservation(
            @PathVariable
            UUID reservationId
    ) {

        Reservation reservation =
                reservationService
                        .getReservationById(
                                reservationId
                        );

        ReservationResponse response =
                new ReservationResponse(
                        reservation.getId(),
                        reservation.getStatus(),
                        "Reservation retrieved successfully."
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        response,
                        "Reservation retrieved successfully."
                )
        );

    }

}