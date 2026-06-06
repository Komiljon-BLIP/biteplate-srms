package com.biteplate.biteplate_api.reservation.api.controller;

import com.biteplate.biteplate_api.reservation.api.request.CreateReservationRequest;
import com.biteplate.biteplate_api.reservation.api.response.ReservationResponse;
import com.biteplate.biteplate_api.shared.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @PostMapping
    public ResponseEntity<ApiResponse<ReservationResponse>>
    createReservation(
            @RequestBody
            CreateReservationRequest request
    ) {

        ReservationResponse response =
                new ReservationResponse(
                        null,
                        null,
                        "Reservation request accepted."
                );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        response
                )
        );

    }

}