package com.medisite.medicos.controller;

import com.medisite.medicos.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/doctors")
public interface DoctorController {
    @GetMapping
    List<Doctor> getAllDoctors();

    @GetMapping("/specialty/{specialty}")
    List<Doctor> getDoctorsBySpecialty(@PathVariable String specialty);

    @GetMapping("/city/{city}")
    List<Doctor> getDoctorsByCity(@PathVariable String city);

    @GetMapping("/availability")
    List<Doctor> getDoctorsByAvailability(@RequestParam String availability);

    @PostMapping
    Doctor createDoctor(@RequestBody Doctor doctor);
}
