package com.univacinas.appointment;

import com.univacinas.error.*;
import com.univacinas.user.User;
import com.univacinas.user.UserService;
import com.univacinas.vaccine.Vaccine;
import com.univacinas.vaccine.VaccineService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final VaccineService vaccineService;
    private final CustomAppointmentRepository customAppointmentRepository;

    public Appointment createAppointment(CreateAppointmentRequest request) {
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

        boolean alreadyHasAppointment = appointmentRepository.existsAppointmentsByPatientAndStartDateTimeAndEndDateTimeAndStatus(patient, expectedStartDateTime, expectedEndDateTime, AppointmentStatus.SCHEDULED);
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

    public List<Appointment> listAppointments(Optional<Long> patientId, Optional<AppointmentStatus> status) {
        return customAppointmentRepository.findByOptionalFilters(patientId, status);
    }

    public Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(AppointmentNotFoundException::new);
    }

    public Appointment cancelAppointment(Long appointmentId) {
        Appointment appointment = getAppointment(appointmentId);

        if (appointment.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new InvalidAppointmentStatusException();
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    public Appointment completeAppointment(Long appointmentId) {
        Appointment appointment = getAppointment(appointmentId);

        if (appointment.getStatus() != AppointmentStatus.SCHEDULED) {
            throw new InvalidAppointmentStatusException();
        }

        if (!appointment.getVaccine().hasStock()) {
            throw new VaccineOutOfStockException("Vacina sem estoque para encerramento de consulta agendada.");
        }

        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointment.setVaccine(vaccineService.reduceStock(appointment.getVaccine()));

        return appointmentRepository.save(appointment);
    }
}
