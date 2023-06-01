package com.example.admin.service;

import java.util.Optional;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.admin.config.ResourceNotFoundException;
import com.example.admin.entity.EmployeeDetails;
import com.example.admin.repo.EmployeeRepo;


@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<EmployeeDetails> employee = repository.findByEmail(email);
        return
                employee
                        .map(EmployeeUserDetails::new)
                        .orElseThrow(() -> new ResourceNotFoundException("UNAUTHORIZED"));
    }
}
