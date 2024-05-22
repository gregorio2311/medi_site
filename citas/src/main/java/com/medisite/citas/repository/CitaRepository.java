package com.medisite.citas.repository;

import com.medisite.citas.model.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {
    List<Cita> findByDoctorName(String doctorName);
    List<Cita> findByPatientEmail(String patientEmail);
    List<Cita> findByCitaDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
