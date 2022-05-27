package com.digi.leaves.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digi.leaves.exception.DataNotFoundException;
import com.digi.leaves.model.Employee;
import com.digi.leaves.modelDTO.DataRecordState;
import com.digi.leaves.modelDTO.EmployeeDTO;
import com.digi.leaves.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRep;

	public void saveConsumed(EmployeeDTO empConsumed) {
		if (empConsumed.getDataState() == DataRecordState.ADDED) {
			saveEmployee(empConsumed);
		} else if (empConsumed.getDataState() == DataRecordState.UPDATED) {
			editEmployee(empConsumed);
		}else {
			deleteEmployee(empConsumed);
		}
		
	}

	private void deleteEmployee(EmployeeDTO empConsumed) {
		Optional<Employee>empOpt=empRep.findById(empConsumed.getId());
		Employee emp;
		if(empOpt.isEmpty()) {
			emp=saveEmployee(empConsumed);
		}else {
			emp=empOpt.get();
		}
		emp.setTerminated(true);
		empRep.save(emp);
	}

	private void editEmployee(EmployeeDTO empConsumed) {
		Optional<Employee>empOpt=empRep.findById(empConsumed.getId());
		if(empOpt.isEmpty()) {
			saveEmployee(empConsumed);
		}else {
			Employee emp=empOpt.get();
			emp.setName(empConsumed.getName());
		}
	}

	private Employee saveEmployee(EmployeeDTO empConsumed) {
		Employee emp=new Employee(empConsumed.getId(), empConsumed.getName(), false);
		return empRep.save(emp);
	}

	public Employee getEmployeeLeaves(int id) {
		
		return empRep.findEmployeeLeaves(id);
	}

	public Employee getEmployee(Integer id) {
		Optional<Employee>empOpt=empRep.findById(id);
		if(empOpt.isEmpty()) {
			throw new DataNotFoundException("id="+id);
		}else {
			return empOpt.get();
		}
	}

}
