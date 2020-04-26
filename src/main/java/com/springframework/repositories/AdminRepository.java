package com.springframework.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springframework.domain.Admin;
import com.springframework.domain.Doctor;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmailAddress(String deptName);
	
	Admin findByPasswordAndEmailAddress(String password, String emailAddress);
}
