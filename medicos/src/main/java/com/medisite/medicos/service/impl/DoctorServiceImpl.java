package com.medisite.medicos.service.impl;

import com.medisite.medicos.model.Doctor;
import com.medisite.medicos.repository.DoctorRepository;
import com.medisite.medicos.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    @Override
    public List<Doctor> findByCity(String city) {
        return doctorRepository.findByCity(city);
    }

    @Override
    public List<Doctor> findByAvailability(String availability) {
        return doctorRepository.findByAvailabilityContaining(availability);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
