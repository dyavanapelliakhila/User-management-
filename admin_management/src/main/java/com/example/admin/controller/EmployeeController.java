package com.example.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.config.ResourceNotFoundException;
import com.example.admin.model.AuthRequest;
import com.example.admin.model.EmployeeDto;
import com.example.admin.service.EmployeeServiceImpl;
import com.example.admin.service.JwtService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping("/authenticate")
	public String authenticate(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = null;
		try {
			authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		} catch (Exception e) {
			throw new ResourceNotFoundException("Authentication failed !");
		}

		if (authenticate.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getEmail());
		} else {
			throw new ResourceNotFoundException("Authentication failed !");
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeDto employeeDto) {
		return ResponseEntity.ok(employeeServiceImpl.createEmployee(employeeDto));

	}

	
	@PreAuthorize("hasAuthority('ROLE_EMP')")
	@GetMapping("/get")
	public ResponseEntity<Object> getEmployeeByEmail(@RequestParam(name = "email") String email) {
		return ResponseEntity.ok(employeeServiceImpl.getEmployeeByEmail(email));

	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/get/all")
	public ResponseEntity<Object> getEmployeesAll() {
		return ResponseEntity.ok(employeeServiceImpl.getEmployeeAll());

	}
	
	 @PutMapping("/update")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public String updateRoles(@RequestBody EmployeeDto employee) {
	        return employeeServiceImpl.changeNameOfEmployee(employee);
	    }

}
