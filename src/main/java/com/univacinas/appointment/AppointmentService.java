package com.univacinas.appointment;

import com.univacinas.error.InvalidAppointmentDateException;
import com.univacinas.error.UserAlreadyHasAppointmentException;
import com.univacinas.error.UserIsNotPatientException;
import com.univacinas.error.VaccineOutOfStockException;
import com.univacinas.user.User;
import com.univacinas.user.UserService;
import com.univacinas.vaccine.Vaccine;
import com.univacinas.vaccine.VaccineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final VaccineService vaccineService;

    public Appointment createAppointment(CreateAppointmentRequest request) {
        log.info("Criando agendamento para request {}", request);
        User patient = userService.findById(request.getPatientId());
        if (!patient.isPatient()) {
            throw new UserIsNotPatientException();
        }

        Vaccine vaccine = vaccineService.findById(request.getVaccineId());

        if (!vaccine.hasStock()) {
            throw new VaccineOutOfStockException();
        }

        LocalDateTime expectedStartDateTime = request.getDateTime().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime expectedEndDateTime = expectedStartDateTime.plusMinutes(30);

        //Horário deve ser de meia em meia hora
        if (expectedStartDateTime.getMinute() != 0 && expectedStartDateTime.getMinute() != 30 || expectedEndDateTime.getSecond() != 0) {
            throw new InvalidAppointmentDateException();
        }

        if (expectedStartDateTime.isBefore(LocalDateTime.now().plusMinutes(1))) {
            throw new InvalidAppointmentDateException("Horário de agendamento deve ser futuro.");
        }


        boolean alreadyHasAppointment = appointmentRepository.existsAppointmentsByPatientAndStartDateTimeAndEndDateTime(patient, expectedStartDateTime, expectedEndDateTime);
        if (alreadyHasAppointment) {
            throw new UserAlreadyHasAppointmentException();
        }

        User nurse = userService.findNurse();

        Appointment appointment = Appointment.builder()
            .patient(patient)
            .nurse(nurse)
            .vaccine(vaccine)
            .status(AppointmentStatus.SCHEDULED)
            .startDateTime(expectedStartDateTime)
            .endDateTime(expectedEndDateTime)
            .build();

        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
