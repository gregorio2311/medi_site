package com.medisite.medicos.controller.impl;

import com.medisite.medicos.controller.DoctorController;
import com.medisite.medicos.model.Doctor;
import com.medisite.medicos.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorControllerImpl implements DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorService.findBySpecialty(specialty);
    }

    @Override
    public List<Doctor> getDoctorsByCity(String city) {
        return doctorService.findByCity(city);
    }

    @Override
    public List<Doctor> getDoctorsByAvailability(String availability) {
        return doctorService.findByAvailability(availability);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorService.save(doctor);
    }
}
