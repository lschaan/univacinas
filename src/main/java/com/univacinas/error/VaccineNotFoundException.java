package com.univacinas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Vacina não encontrada.")
public class VaccineNotFoundException extends RuntimeException {
    public VaccineNotFoundException(String message) {
        super(message);
    }

    public VaccineNotFoundException() {
        super("Vacina não encontrada");
    }
}
