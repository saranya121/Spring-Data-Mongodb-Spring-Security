package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * List all the employee list
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping("/displayAll")
	public String displayAllEmployees(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		map.addAttribute("employeeList", employeeService.displayAllEmployees());
		return "display-employee";
	}

	/**
	 * Add New Employee Page
	 * 
	 * @return
	 */
	@GetMapping({ "/addEmployee", "/index", "/" })
	public String addEmployee(ModelMap model) {
		model.addAttribute("employeeDto", new EmployeeDto());
		return "add-edit-employee";
	}

	/**
	 * Save a Employee
	 * 
	 * @param employeeDto
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/saveEmployee")
	public String saveEmployee(EmployeeDto employeeDto, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirAttrs) {
		try {
			String returnStatus = employeeService.saveEmployee(employeeDto);
			if (returnStatus.equalsIgnoreCase("exist already"))
				redirAttrs.addFlashAttribute("error", "Employee ID already exist");
			else if (returnStatus.equalsIgnoreCase("saved"))
				redirAttrs.addFlashAttribute("status", "Employee Added Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/displayAll";
	}

	/**
	 * Edit a Employee
	 * 
	 * @param employeeDto
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/editEmployee")
	public String editEmployee(@RequestParam long empId, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		try {
			model.addAttribute("employeeDto", employeeService.displayEmployeeById(empId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add-edit-employee";
	}

	/**
	 * Update a Employee
	 * 
	 * @param employeeDto
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/updateEmployee")
	public String updateEmployee(EmployeeDto employeeDto, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirAttrs) {	

		try {
			String returnStatus = employeeService.updateEmployee(employeeDto);
			if (returnStatus.equalsIgnoreCase("updated"))
				redirAttrs.addFlashAttribute("status", "Employee Updated Successfully");
			else
				redirAttrs.addFlashAttribute("error", "Employee ID not exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/displayAll";
	}

	/**
	 * Delete a Employee
	 * 
	 * @param employeeDto
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam long empId, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirAttrs) {
		try {
			String returnStatus = employeeService.deleteEmployee(empId);
			if (returnStatus.equalsIgnoreCase("deleted"))
				redirAttrs.addFlashAttribute("info", "Employee Deleted Successfully");
			else
				redirAttrs.addFlashAttribute("error", "Employee ID not exist");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/displayAll";
	}

}
