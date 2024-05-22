package com.medisite.medicos.controller.impl;
import com.medisite.medicos.model.Doctor;
import com.medisite.medicos.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorControllerImpl {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }

    @GetMapping("/specialty")
    public List<Doctor> getDoctorsBySpecialty(@RequestParam String specialty) {
        return doctorService.findBySpecialty(specialty);
    }

    @GetMapping("/city")
    public List<Doctor> getDoctorsByCity(@RequestParam String city) {
        return doctorService.findByCity(city);
    }

    @GetMapping("/availability")
    public List<Doctor> getDoctorsByAvailability(@RequestParam String time) {
        return doctorService.findByAvailability(time);
    }

    @GetMapping("/specialties")
    public List<String> getAllSpecialties() {
        return doctorService.findAllSpecialties();
    }

    @GetMapping("/filter")
    public List<Doctor> getDoctorsBySpecialtyAndCityAndAvailability(
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String availability) {
        return doctorService.findBySpecialtyAndCityAndAvailability(specialty, city, availability);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}
