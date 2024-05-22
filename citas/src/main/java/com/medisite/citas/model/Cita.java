package com.medisite.citas.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doctorName;
    private Long doctorId;
    private String patientName;
    private String patientEmail;
    private LocalDateTime citaDate;
    private String status;

    // Getters and setters

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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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

    public String getDay() {
        if (citaDate != null) {
            return citaDate.getDayOfWeek().toString();
        }
        return null;
    }

    public String getStartTime() {
        if (citaDate != null) {
            return citaDate.toLocalTime().toString();
        }
        return null;
    }

    public String getEndTime() {
        if (citaDate != null) {
            return citaDate.plusHours(1).toLocalTime().toString();  // Asumiendo que la cita dura una hora
        }
        return null;
    }
}
