package com.medisite.medicos.controller.impl;

import com.medisite.medicos.model.Doctor;
import com.medisite.medicos.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all specialties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all specialties",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @GetMapping("/specialties")
    public ResponseEntity<List<String>> getAllSpecialties() {
        return new ResponseEntity<>(doctorService.findAllSpecialties(), HttpStatus.OK);
    }

    @Operation(summary = "Get all doctors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all doctors",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class))})
    })
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get doctors by specialty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found doctors by specialty",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class))}),
            @ApiResponse(responseCode = "404", description = "Doctors not found",
                    content = @Content)
    })
    @GetMapping("/specialty")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialty(@RequestParam String specialty) {
        return new ResponseEntity<>(doctorService.findBySpecialty(specialty), HttpStatus.OK);
    }

    @Operation(summary = "Get doctors by city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found doctors by city",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class))}),
            @ApiResponse(responseCode = "404", description = "Doctors not found",
                    content = @Content)
    })
    @GetMapping("/city")
    public ResponseEntity<List<Doctor>> getDoctorsByCity(@RequestParam String city) {
        return new ResponseEntity<>(doctorService.findByCity(city), HttpStatus.OK);
    }

    @Operation(summary = "Get doctors by availability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found doctors by availability",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class))}),
            @ApiResponse(responseCode = "404", description = "Doctors not found",
                    content = @Content)
    })
    @GetMapping("/availability")
    public ResponseEntity<List<Doctor>> getDoctorsByAvailability(
            @Parameter(description = "Day of availability") @RequestParam String day,
            @Parameter(description = "Start time of availability") @RequestParam String startTime,
            @Parameter(description = "End time of availability") @RequestParam String endTime,
            @Parameter(description = "Doctor ID to search for availability") @RequestParam Long doctorId) {
        return new ResponseEntity<>(doctorService.findByAvailability(day, startTime, endTime, doctorId), HttpStatus.OK);
    }

    @Operation(summary = "Get doctors by specialty, city, and availability")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found doctors by specialty, city, and availability",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class))}),
            @ApiResponse(responseCode = "404", description = "Doctors not found",
                    content = @Content)
    })
    @GetMapping("/filter")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialtyAndCityAndAvailability(
            @Parameter(description = "Specialty to search for") @RequestParam String specialty,
            @Parameter(description = "City to search for") @RequestParam String city,
            @Parameter(description = "Day of availability") @RequestParam String day,
            @Parameter(description = "Start time of availability") @RequestParam String startTime,
            @Parameter(description = "End time of availability") @RequestParam String endTime) {
        return new ResponseEntity<>(doctorService.findBySpecialtyAndCityAndAvailability(specialty, city, day, startTime, endTime), HttpStatus.OK);
    }

    @Operation(summary = "Create a new doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class))})
    })
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.save(doctor), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Doctor.class))}),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@Parameter(description = "ID of the doctor to update") @PathVariable Long id, @RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.updateDoctor(id, doctor), HttpStatus.OK);
    }

    @Operation(summary = "Delete a doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Doctor deleted",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@Parameter(description = "ID of the doctor to delete") @PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
