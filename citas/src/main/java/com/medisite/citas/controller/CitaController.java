package com.medisite.citas.controller;

import com.medisite.citas.model.Cita;
import com.medisite.citas.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all appointments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all appointments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class)))
    })
    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        return new ResponseEntity<>(citaService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get appointment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the appointment",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(
            @Parameter(description = "ID of the appointment to retrieve") @PathVariable Long id) {
        return new ResponseEntity<>(citaService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get appointments by doctor's name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found appointments by doctor's name",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class)))
    })
    @GetMapping("/doctor")
    public ResponseEntity<List<Cita>> getCitasByDoctorName(
            @Parameter(description = "Name of the doctor") @RequestParam String doctorName) {
        return new ResponseEntity<>(citaService.findByDoctorName(doctorName), HttpStatus.OK);
    }

    @Operation(summary = "Get appointments by patient's email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found appointments by patient's email",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class)))
    })
    @GetMapping("/patient")
    public ResponseEntity<List<Cita>> getCitasByPatientEmail(
            @Parameter(description = "Email of the patient") @RequestParam String patientEmail) {
        return new ResponseEntity<>(citaService.findByPatientEmail(patientEmail), HttpStatus.OK);
    }

    @Operation(summary = "Get appointments by date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found appointments by date range",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "404", description = "Appointments not found", content = @Content)
    })
    @GetMapping("/date")
    public ResponseEntity<List<Cita>> getCitasByDateRange(
            @Parameter(description = "Start date and time") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @Parameter(description = "End date and time") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return new ResponseEntity<>(citaService.findByCitaDateBetween(startDate, endDate), HttpStatus.OK);
    }

    @Operation(summary = "Create an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.save(cita), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cita.class))),
            @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(
            @Parameter(description = "ID of the appointment to update") @PathVariable Long id,
            @RequestBody Cita cita) {
        return new ResponseEntity<>(citaService.updateCita(id, cita), HttpStatus.OK);
    }

    @Operation(summary = "Delete an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Appointment deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Appointment not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(
            @Parameter(description = "ID of the appointment to delete") @PathVariable Long id) {
        citaService.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
