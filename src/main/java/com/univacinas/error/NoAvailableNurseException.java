package com.univacinas.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Nenhum enfermeiro disponível para o agendamento.")
public class NoAvailableNurseException extends RuntimeException {
    public NoAvailableNurseException(String message) {
        super(message);
    }

    public NoAvailableNurseException() {
      super("Nenhum enfermeiro disponível para o agendamento.");
    }
}
