package com.employee.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

@Document(collection="Employee")
@Data
public class EmployeeEntity {
	@Id
	private String id;
	private long employeeId;
	private String employeeName;
	private String employeeEmail;
	private String location;
	private boolean activeStatus;

}
