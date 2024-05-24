package com.medisite.medicos.service.impl;

import com.medisite.medicos.model.Doctor;
import com.medisite.medicos.repository.DoctorRepository;
import com.medisite.medicos.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<String> findAllSpecialties() {
        return doctorRepository.findAllSpecialties();
    }
    @Override
    public List<Doctor> findBySpecialtyAndCityAndAvailability(String specialty, String city, String day, String startTime, String endTime) {
        return doctorRepository.findBySpecialtyAndCityAndAvailability(specialty, city, day, startTime, endTime);
    }

    @Override
    public List<Doctor> findByAvailability(String day, String startTime, String endTime, Long doctorId) {
        return doctorRepository.findByAvailability(day, startTime, endTime, doctorId);
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        return doctors;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found with id " + id));
        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialty(doctor.getSpecialty());
        existingDoctor.setCity(doctor.getCity());
        existingDoctor.setAvailability(doctor.getAvailability());
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Doctor not found with id " + id);
        }
    }

    @Override
    public List<Doctor> findBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    @Override
    public List<Doctor> findByCity(String city) {
        return doctorRepository.findByCity(city);
    }
}
