package com.univacinas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Horário de agendamento inválido. Horário deve encerrar com minuto xx:00 ou xx:30. Horário deve ser no futuro.")
public class InvalidAppointmentDateException extends RuntimeException {
    public InvalidAppointmentDateException(String message) {
        super(message);
    }

    public InvalidAppointmentDateException() {
        super("Horário de agendamento inválido. Horário deve encerrar com minuto xx:00 ou xx:30.");
    }
}
