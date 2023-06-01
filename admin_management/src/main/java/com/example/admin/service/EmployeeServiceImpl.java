package com.example.admin.service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.admin.entity.EmployeeDetails;
import com.example.admin.model.EmployeeDto;
import com.example.admin.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String createEmployee(EmployeeDto employeeDto) {
		Optional<EmployeeDetails> emOptional = employeeRepo.findByEmail(employeeDto.getEmail());
		if (!emOptional.isPresent()) {
			EmployeeDetails employeeDetails = new EmployeeDetails();
			employeeDetails.setUserName(employeeDto.getUserName());
			employeeDetails.setEmail(employeeDto.getEmail());
			employeeDetails.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
			employeeDetails.setRoles("ROLE_EMP");
			employeeRepo.save(employeeDetails);
			return "user isCreated email " + employeeDetails;
		} else {
			return "user is present with this email " + emOptional.get().getEmail();
		}
	}

	public Object getEmployeeByEmail(String email) {
		Optional<EmployeeDetails> emOptional = employeeRepo.findByEmail(email);
		if (emOptional.isPresent()) {
		return emOptional;
	} else {
		return "user is not  present with this email " + email;
	}
	}

	public Object getEmployeeAll() {
		return employeeRepo.findAll();
	}

	public String changeNameOfEmployee(EmployeeDto employee) {
		Optional<EmployeeDetails> emOptional = employeeRepo.findById(employee.getId());
		if (emOptional.isPresent()) {
			emOptional.get().setUserName(employee.getUserName());
			employeeRepo.save(emOptional.get());
			return "USER IS NAME IS UPDATED";
		} else {
			return "user is not  present with this email " + employee.getId();
		}
	}

}
