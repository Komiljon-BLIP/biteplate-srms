package com.biteplate.biteplate_api.reservation.api.controller;

import com.biteplate.biteplate_api.reservation.api.mapper.ReservationApiMapper;
import com.biteplate.biteplate_api.reservation.api.request.CreateReservationRequest;
import com.biteplate.biteplate_api.reservation.api.response.ReservationResponse;
import com.biteplate.biteplate_api.reservation.application.service.ReservationService;
import com.biteplate.biteplate_api.reservation.domain.model.Reservation;
import com.biteplate.biteplate_api.shared.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationApiMapper apiMapper;

    private final ReservationService reservationService;

    public ReservationController(
            ReservationApiMapper apiMapper,
            ReservationService reservationService
    ) {
        this.apiMapper = apiMapper;
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

        reservationService.createReservation(
                reservation
        );

        ReservationResponse response =
                new ReservationResponse(
                        reservation.getId(),
                        reservation.getStatus(),
                        "Reservation created successfully."
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        response
                )
        );

    }

}