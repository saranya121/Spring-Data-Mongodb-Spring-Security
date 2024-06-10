package com.employee.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employee.model.EmployeeEntity;

public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {

	EmployeeEntity findByEmployeeId(long employeeId);

	List<EmployeeEntity> findByActiveStatus(boolean activeStatus);

}
