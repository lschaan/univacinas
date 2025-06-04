package com.univacinas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Vacina sem estoque.")
public class VaccineOutOfStockException extends RuntimeException {
    public VaccineOutOfStockException(String message) {
        super(message);
    }

    public VaccineOutOfStockException() {
        super("Vacina sem estoque.");
    }
}
