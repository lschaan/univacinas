package com.univacinas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Paciente já possui agendamento marcado para o horário.")
public class UserAlreadyHasAppointmentException extends RuntimeException {
    public UserAlreadyHasAppointmentException(String message) {
        super(message);
    }

    public UserAlreadyHasAppointmentException() {
        super("Paciente já possui agendamento marcado para o horário.");
    }
}
