package com.univacinas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Status de agendamento inválido para a operação.")
public class InvalidAppointmentStatusException extends RuntimeException {
    public InvalidAppointmentStatusException(String message) {
        super(message);
    }

    public InvalidAppointmentStatusException() {
        super("Status de agendamento inválido para a operação.");
    }
}
