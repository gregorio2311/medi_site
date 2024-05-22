package com.medisite.medicos.controller.impl;

import com.medisite.medicos.model.Doctor;
import com.medisite.medicos.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorControllerImpl {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/specialty")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialty(@RequestParam String specialty) {
        return new ResponseEntity<>(doctorService.findBySpecialty(specialty), HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<Doctor>> getDoctorsByCity(@RequestParam String city) {
        return new ResponseEntity<>(doctorService.findByCity(city), HttpStatus.OK);
    }

    @GetMapping("/availability")
    public ResponseEntity<List<Doctor>> getDoctorsByAvailability(@RequestParam String time) {
        return new ResponseEntity<>(doctorService.findByAvailability(time), HttpStatus.OK);
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<String>> getAllSpecialties() {
        return new ResponseEntity<>(doctorService.findAllSpecialties(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialtyAndCityAndAvailability(
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String availability) {
        return new ResponseEntity<>(doctorService.findBySpecialtyAndCityAndAvailability(specialty, city, availability), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.save(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.updateDoctor(id, doctor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
