package com.digi.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digi.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByTerminated(boolean terminated);

}

