package com.biteplate.biteplate_api.shared.exception;

public class ReservationException
        extends RuntimeException {

    public ReservationException(
            String message
    ) {
        super(message);
    }

}