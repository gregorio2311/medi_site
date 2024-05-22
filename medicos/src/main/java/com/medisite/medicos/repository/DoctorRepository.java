package com.medisite.medicos.repository;

import com.medisite.medicos.model.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Query("SELECT DISTINCT d.specialty FROM Doctor d")
    List<String> findAllSpecialties();

    List<Doctor> findBySpecialty(String specialty);

    List<Doctor> findByCity(String city);

    @Query("SELECT d FROM Doctor d WHERE d.availability LIKE %:time%")
    List<Doctor> findByAvailability(String time);

    @Query("SELECT d FROM Doctor d WHERE " +
            "(:specialty IS NULL OR d.specialty = :specialty) AND " +
            "(:city IS NULL OR d.city = :city) AND " +
            "(:availability IS NULL OR d.availability LIKE %:availability%)")
    List<Doctor> findBySpecialtyAndCityAndAvailability(String specialty, String city, String availability);
}

