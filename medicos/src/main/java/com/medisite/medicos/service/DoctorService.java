package com.medisite.medicos.service;
import com.medisite.medicos.model.Doctor;
import java.util.List;

public interface DoctorService {
    List<Doctor> findBySpecialtyAndCityAndAvailability(String specialty, String city, String day, String startTime, String endTime);
    List<Doctor> findByAvailability(String day, String startTime, String endTime, Long doctorId); // Ajuste aquí
    List<Doctor> findAll();
    Doctor save(Doctor doctor);
    Doctor updateDoctor(Long id, Doctor doctor);
    void deleteDoctor(Long id);
    List<Doctor> findBySpecialty(String specialty);
    List<Doctor> findByCity(String city);
    List<String> findAllSpecialties();

}
