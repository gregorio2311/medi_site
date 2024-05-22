package com.medisite.citas.service;

import com.medisite.citas.model.Cita;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaService {
    List<Cita> findAll();
    Cita findById(Long id);
    List<Cita> findByDoctorName(String doctorName);
    List<Cita> findByPatientEmail(String patientEmail);
    List<Cita> findByCitaDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    Cita save(Cita cita);
    Cita updateCita(Long id, Cita cita);
    void deleteCita(Long id);
}
