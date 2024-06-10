package com.employee.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.model.EmployeeEntity;
import com.employee.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/employeeApi")
public class EmployeeAPIController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * To display All Employees
	 * 
	 * @return
	 */
	@GetMapping("/displayAll")
	public ResponseEntity<List<EmployeeEntity>> displayAllEmployees() {
		return ResponseEntity.ok(employeeService.displayAllEmployees());
	}

	/**
	 * To save Employee
	 * 
	 * @param employeeDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/save")
	public ResponseEntity<?> saveEmployee(EmployeeDto employeeDto) throws Exception {
		String returnStatus = employeeService.saveEmployee(employeeDto);
		if (returnStatus.equalsIgnoreCase("saved"))
			returnStatus = "Employee Saved Successfully";
		return ResponseEntity.ok(returnStatus);

	}

	/**
	 * To get Employee By ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/displayById/{id}")
	public ResponseEntity<EmployeeDto> displayEmployeeById(@PathVariable long id) {
		return ResponseEntity.ok(employeeService.displayEmployeeById(id));
	}

	/**
	 * To update Employee
	 * 
	 * @param employeeDto
	 * @param request
	 * @param response
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<String> updateEmployee(EmployeeDto employeeDto, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String returnStatus = employeeService.updateEmployee(employeeDto);
			if (returnStatus.equalsIgnoreCase("updated"))
				returnStatus = "Employee updated Successfully";
			else
				returnStatus = "Employee ID not exist";
			return ResponseEntity.ok(returnStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Delete A Employee
	 * 
	 * @param empId
	 * @return
	 */
	@DeleteMapping("/delete/{empId}")
	public String deleteEmployee(@PathVariable long empId) {
		try {
			String returnStatus = employeeService.deleteEmployee(empId);
			if (returnStatus.equalsIgnoreCase("deleted"))
				returnStatus = "Employee Deleted Successfully";
			else
				returnStatus = "Employee ID not exist";
			return returnStatus;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
