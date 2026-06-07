package com.biteplate.biteplate_api.shared.exception;

import com.biteplate.biteplate_api.shared.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ApiResponse<Object>>
    handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        String errorMessage =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        return ResponseEntity
                .status(
                        HttpStatus.BAD_REQUEST
                )
                .body(

                        new ApiResponse<>(

                                false,

                                null,

                                errorMessage

                        )

                );

    }

    @ExceptionHandler(
            InvalidReservationException.class
    )
    public ResponseEntity<ApiResponse<Object>>
    handleInvalidReservation(
            InvalidReservationException ex
    ) {

        return ResponseEntity
                .status(
                        HttpStatus.BAD_REQUEST
                )
                .body(

                        new ApiResponse<>(

                                false,

                                null,

                                ex.getMessage()

                        )

                );

    }

    @ExceptionHandler(
            Exception.class
    )
    public ResponseEntity<ApiResponse<Object>>
    handleGeneralException(
            Exception ex
    ) {

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(

                        new ApiResponse<>(

                                false,

                                null,

                                "Internal server error."

                        )

                );

    }

}