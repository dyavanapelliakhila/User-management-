package com.example.admin.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.admin.entity.EmployeeDetails;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeDetails, Integer> {

	Optional<EmployeeDetails> findByEmail(String email);

}
