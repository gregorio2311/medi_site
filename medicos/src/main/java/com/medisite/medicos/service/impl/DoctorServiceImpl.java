package com.medisite.medicos.service.impl;

import com.medisite.medicos.utils.exception.DoctorNotFoundException;
import com.medisite.medicos.model.Doctor;
import com.medisite.medicos.repository.DoctorRepository;
import com.medisite.medicos.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
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
    public List<Doctor> findByAvailability(String time) {
        return doctorRepository.findByAvailability(time);
    }

    @Override
    public List<String> findAllSpecialties() {
        return doctorRepository.findAllSpecialties();
    }

    @Override
    public List<Doctor> findBySpecialtyAndCityAndAvailability(String specialty, String city, String availability) {
        return doctorRepository.findBySpecialtyAndCityAndAvailability(specialty, city, availability);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(id);
        if (existingDoctor.isPresent()) {
            Doctor updatedDoctor = existingDoctor.get();
            updatedDoctor.setName(doctor.getName());
            updatedDoctor.setCity(doctor.getCity());
            updatedDoctor.setSpecialty(doctor.getSpecialty());
            updatedDoctor.setAvailability(doctor.getAvailability());
            return doctorRepository.save(updatedDoctor);
        } else {
            throw new DoctorNotFoundException("Doctor not found with id " + id);
        }
    }

    @Override
    public void deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        } else {
            throw new DoctorNotFoundException("Doctor not found with id " + id);
        }
    }
}
