package com.univacinas.appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentStats {

    private long totalAppointments;
    private long distinctPatients;
    private long distinctNurses;
}
