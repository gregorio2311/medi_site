package com.medisite.medicos.controller;

import com.medisite.medicos.model.Doctor;
import java.util.List;

public interface DoctorController {

    List<Doctor> getAllDoctors();

    List<Doctor> getDoctorsBySpecialty(String specialty);

    List<Doctor> getDoctorsByCity(String city);

    List<Doctor> getDoctorsByAvailability(String start, String end);

    List<String> getAllSpecialties();

    List<Doctor> getDoctorsBySpecialtyAndCityAndAvailability(String specialty, String city, String availability);

    Doctor createDoctor(Doctor doctor);
}
