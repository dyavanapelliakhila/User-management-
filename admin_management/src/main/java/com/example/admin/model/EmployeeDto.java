package com.example.admin.model;

import lombok.Data;

@Data
public class EmployeeDto {
	private Integer id;
	private String userName;
	private String email;
	private String password;
	private String roles;
}
