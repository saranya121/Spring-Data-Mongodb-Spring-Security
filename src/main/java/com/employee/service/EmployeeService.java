package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.exception.RecordAlreadyExistException;
import com.employee.model.EmployeeEntity;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<EmployeeEntity> displayAllEmployees() {
		return employeeRepository.findByActiveStatus(true);
	}

	public String saveEmployee(EmployeeDto employeeDto) throws RecordAlreadyExistException {
		EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeDto.getEmployeeId());
		if (employeeEntity != null) {
			return "exist already";
		}
		employeeEntity = new EmployeeEntity();
		employeeEntity.setEmployeeEmail(employeeDto.getEmployeeEmail());
		employeeEntity.setEmployeeName(employeeDto.getEmployeeName());
		employeeEntity.setEmployeeId(employeeDto.getEmployeeId());
		employeeEntity.setLocation(employeeDto.getLocation());
		employeeEntity.setActiveStatus(true);
		employeeRepository.save(employeeEntity);
		return "saved";
	}

	public EmployeeDto displayEmployeeById(long employeeId) {
		EmployeeDto employeeDto = new EmployeeDto();
		EmployeeEntity entity = employeeRepository.findByEmployeeId(employeeId);
		if (entity != null) {
			employeeDto.setEmployeeEmail(entity.getEmployeeEmail());
			employeeDto.setEmployeeName(entity.getEmployeeName());
			employeeDto.setEmployeeId(entity.getEmployeeId());
			employeeDto.setLocation(entity.getLocation());
		}
		return employeeDto;
	}

	public String updateEmployee(EmployeeDto employeeDto) {
		EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(employeeDto.getEmployeeId());
		if (employeeEntity != null) {
			employeeEntity.setEmployeeEmail(employeeDto.getEmployeeEmail());
			employeeEntity.setEmployeeName(employeeDto.getEmployeeName());
			employeeEntity.setEmployeeId(employeeDto.getEmployeeId());
			employeeEntity.setLocation(employeeDto.getLocation());
			employeeRepository.save(employeeEntity);
			return "updated";
		} else
			return "not exist";

	}

	public String deleteEmployee(long empId) {
		EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(empId);
		if (employeeEntity != null)
			employeeRepository.delete(employeeEntity);
		else
			return "not exist";

		return "deleted";
	}

}
