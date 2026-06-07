package com.biteplate.biteplate_api.shared.exception;

public class InvalidReservationException
        extends RuntimeException {

    public InvalidReservationException(
            String message
    ) {
        super(message);
    }

}