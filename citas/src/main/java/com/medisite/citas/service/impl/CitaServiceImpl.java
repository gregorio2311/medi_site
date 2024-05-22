package com.medisite.citas.service.impl;

import com.medisite.citas.utils.exception.CitaNotFoundException;
import com.medisite.citas.utils.exception.DoctorNotAvailableException;
import com.medisite.citas.model.Cita;
import com.medisite.citas.model.Doctor;
import com.medisite.citas.repository.CitaRepository;
import com.medisite.citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Cita> findAll() {
        return (List<Cita>) citaRepository.findAll();
    }

    @Override
    public Cita findById(Long id) {
        return citaRepository.findById(id).orElseThrow(() -> new CitaNotFoundException("Cita not found with id " + id));
    }

    @Override
    public List<Cita> findByDoctorName(String doctorName) {
        return citaRepository.findByDoctorName(doctorName);
    }

    @Override
    public List<Cita> findByPatientEmail(String patientEmail) {
        return citaRepository.findByPatientEmail(patientEmail);
    }

    @Override
    public List<Cita> findByCitaDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return citaRepository.findByCitaDateBetween(startDate, endDate);
    }

    @Override
    public Cita save(Cita cita) {
        // Verificar disponibilidad del doctor
        String url = "http://localhost:8080/doctors/availability?day=" + cita.getDay() + "&startTime=" + cita.getStartTime() + "&endTime=" + cita.getEndTime() + "&doctorId=" + cita.getDoctorId();
        ResponseEntity<List<Doctor>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Doctor>>() {});

        if (response.getBody() != null && !response.getBody().isEmpty()) {
            if (cita.getCitaDate() == null) {
                cita.setCitaDate(LocalDateTime.now()); // Establece la fecha actual si no está definida
            }
            return citaRepository.save(cita);
        } else {
            throw new DoctorNotAvailableException("Doctor not available at the requested time.");
        }
    }

    @Override
    public Cita updateCita(Long id, Cita cita) {
        Cita existingCita = findById(id);
        existingCita.setDoctorName(cita.getDoctorName());
        existingCita.setDoctorId(cita.getDoctorId());
        existingCita.setPatientName(cita.getPatientName());
        existingCita.setPatientEmail(cita.getPatientEmail());
        existingCita.setCitaDate(cita.getCitaDate());
        existingCita.setStatus(cita.getStatus());
        return citaRepository.save(existingCita);
    }

    @Override
    public void deleteCita(Long id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
        } else {
            throw new CitaNotFoundException("Cita not found with id " + id);
        }
    }
}
