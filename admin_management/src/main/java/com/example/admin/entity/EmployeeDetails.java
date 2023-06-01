package com.example.admin.entity;

import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GeneratorType;

import lombok.Data;

@Data
@Entity
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private Integer id;
	private String userName;
	private String email;
	private String password;
	private String roles;
}
