package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@Setter
public class CustomServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6827913842927095234L;

    private HttpStatus status;

    public CustomServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
