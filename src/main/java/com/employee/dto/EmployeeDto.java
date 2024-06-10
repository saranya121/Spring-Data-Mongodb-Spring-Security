package com.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
	private Long employeeId;
	private String employeeName;
	private String employeeEmail;
	private String location;
}
