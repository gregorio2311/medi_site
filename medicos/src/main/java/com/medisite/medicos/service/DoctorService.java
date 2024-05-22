package com.medisite.medicos.service;

import com.medisite.medicos.model.Doctor;
import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();
    List<Doctor> findBySpecialty(String specialty);
    List<Doctor> findByCity(String city);
    List<Doctor> findByAvailability(String availability);
    Doctor save(Doctor doctor);
}
