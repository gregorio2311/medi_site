package com.medisite.citas.controller;

import com.medisite.citas.model.Cita;
import com.medisite.citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        return new ResponseEntity<>(citaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long id) {
        return new ResponseEntity<>(citaService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<Cita>> getCitasByDoctorName(@RequestParam String doctorName) {
        return new ResponseEntity<>(citaService.findByDoctorName(doctorName), HttpStatus.OK);
    }

    @GetMapping("/patient")
    public ResponseEntity<List<Cita>> getCitasByPatientEmail(@RequestParam String patientEmail) {
        return new ResponseEntity<>(citaService.findByPatientEmail(patientEmail), HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Cita>> getCitasByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return new ResponseEntity<>(citaService.findByCitaDateBetween(startDate, endDate), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.save(cita), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable Long id, @RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.updateCita(id, cita), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
