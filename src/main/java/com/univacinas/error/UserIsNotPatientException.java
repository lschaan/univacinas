package com.univacinas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Usuário não é um paciente.")
public class UserIsNotPatientException extends RuntimeException {
    public UserIsNotPatientException(String message) {
        super(message);
    }

    public UserIsNotPatientException() {
        super("Usuário não é um paciente.");
    }
}
