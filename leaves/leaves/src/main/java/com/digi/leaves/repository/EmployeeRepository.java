package com.digi.leaves.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.digi.leaves.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query ("SELECT DISTINCT e FROM Employee e JOIN FETCH e.leaves l WHERE e.id=?1")
	Employee findEmployeeLeaves (int id); 
	
}
