package com.medisite.citas.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctorName")
    private String doctorName;

    @Column(name = "patientName")
    private String patientName;

    @Column(name = "patientEmail")
    private String patientEmail;

    @Column(name = "citaDate")
    private LocalDateTime citaDate;

    @Column(name = "status")
    private String status;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public LocalDateTime getCitaDate() {
        return citaDate;
    }

    public void setCitaDate(LocalDateTime citaDate) {
        this.citaDate = citaDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
