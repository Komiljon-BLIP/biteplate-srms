package com.biteplate.biteplate_api.reservation.api.controller;

import com.biteplate.biteplate_api.infrastructure.concurrency.ConcurrencyManager;
import com.biteplate.biteplate_api.reservation.api.mapper.ReservationApiMapper;
import com.biteplate.biteplate_api.reservation.api.request.CreateReservationRequest;
import com.biteplate.biteplate_api.reservation.api.response.ReservationResponse;
import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationApiMapper apiMapper;

    private final ConcurrencyManager concurrencyManager;

    public ReservationController(
            ReservationApiMapper apiMapper,
            ConcurrencyManager concurrencyManager
    ) {
        this.apiMapper = apiMapper;
        this.concurrencyManager = concurrencyManager;
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
                        response
                )
        );

    }

    @GetMapping("/queue/status")
    public ResponseEntity<ApiResponse<Integer>>
    getQueueStatus() {

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        concurrencyManager.getQueueSize()

                )

        );

    }
    @GetMapping("/queue/details")
    public ResponseEntity<ApiResponse<String>> getQueueDetails() {

        String message =
                "Current queue size: "
                        + concurrencyManager.getQueueSize();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        message
                )
        );

    }

}