package com.medisite.medicos.service;
import com.medisite.medicos.model.Doctor;
import java.util.List;

public interface DoctorService {

    List<Doctor> findAll();

    List<Doctor> findBySpecialty(String specialty);

    List<Doctor> findByCity(String city);

    List<Doctor> findByAvailability(String time);

    List<String> findAllSpecialties();

    List<Doctor> findBySpecialtyAndCityAndAvailability(String specialty, String city, String availability);

    Doctor save(Doctor doctor);

    Doctor updateDoctor(Long id, Doctor doctor);

    void deleteDoctor(Long id);
}
