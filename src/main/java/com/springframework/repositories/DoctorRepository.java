package com.springframework.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springframework.domain.Doctor;
import com.springframework.domain.User;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Override
	Optional<Doctor> findById(Long id);

    List<Doctor> findByFNameContainingIgnoreCase(String name);

}
