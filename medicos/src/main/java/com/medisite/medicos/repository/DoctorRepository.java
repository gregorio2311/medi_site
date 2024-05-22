package com.medisite.medicos.repository;

import com.medisite.medicos.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Query("SELECT DISTINCT d.specialty FROM Doctor d")
    List<String> findAllSpecialties();

    @Query("SELECT d FROM Doctor d JOIN d.availability a WHERE d.specialty = :specialty AND d.city = :city AND a.day = :day AND a.startTime <= :startTime AND a.endTime >= :endTime")
    List<Doctor> findBySpecialtyAndCityAndAvailability(@Param("specialty") String specialty, @Param("city") String city, @Param("day") String day, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query("SELECT d FROM Doctor d WHERE d.specialty = :specialty")
    List<Doctor> findBySpecialty(@Param("specialty") String specialty);

    @Query("SELECT d FROM Doctor d WHERE d.city = :city")
    List<Doctor> findByCity(@Param("city") String city);

    @Query("SELECT d FROM Doctor d JOIN d.availability a WHERE a.day = :day AND a.startTime <= :startTime AND a.endTime >= :endTime AND d.id = :doctorId")
    List<Doctor> findByAvailability(@Param("day") String day, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("doctorId") Long doctorId);
}

