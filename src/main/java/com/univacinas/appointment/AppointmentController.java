package com.univacinas.appointment;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@CrossOrigin
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ATTENDANT', 'ADMIN')")
    public AppointmentResponse createAppointment(@RequestBody CreateAppointmentRequest request) {
        return AppointmentResponse.from(appointmentService.createAppointment(request));
    }

    @GetMapping("/{id}")
    public AppointmentResponse getAppointment(@PathVariable Long id) {
        return AppointmentResponse.from(appointmentService.getAppointment(id));
    }

    @GetMapping("/list")
    public List<AppointmentResponse> listAppointments(@RequestParam Optional<Long> patientId, @RequestParam Optional<AppointmentStatus> status) {
        return appointmentService.listAppointments(patientId, status).stream().map(AppointmentResponse::from).toList();
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasAnyRole('ATTENDANT', 'ADMIN', 'NURSE')")
    public AppointmentResponse cancelAppointment(@PathVariable Long id) {
        return AppointmentResponse.from(appointmentService.cancelAppointment(id));
    }

    @PostMapping("/complete/{id}")
    @PreAuthorize("hasAnyRole('ATTENDANT', 'ADMIN', 'NURSE')")
    public AppointmentResponse completeAppointment(@PathVariable Long id) {
        return AppointmentResponse.from(appointmentService.completeAppointment(id));
    }
}
