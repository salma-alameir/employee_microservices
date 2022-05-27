package com.digi.employee.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digi.employee.exception.DataNotFoundException;
import com.digi.employee.model.Employee;
import com.digi.employee.modelDTO.DataRecordState;
import com.digi.employee.modelDTO.EmployeeDTO;
import com.digi.employee.producer.EmployeeProducer;
import com.digi.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRep;
	
	@Autowired
	EmployeeProducer empProduce;

	public Employee saveEmployee(Employee employee) {
		Employee savedEmp=empRep.save(employee);
		empProduce.send(createDTO(employee,DataRecordState.ADDED));
		return savedEmp;
	}

	private EmployeeDTO createDTO(Employee employee, DataRecordState state) {
		EmployeeDTO createdDTO=new EmployeeDTO(state,employee.getName(),new Date(),employee.getId());
		return createdDTO;
	}

	public Employee updateEmployee(int id, Employee emp) {
		Employee oldEmp=getEmployee(id);
		oldEmp.setBirthDate(emp.getBirthDate());
		oldEmp.setEmail(emp.getEmail());
		oldEmp.setJoinDate(emp.getJoinDate());
		oldEmp.setName(emp.getName());
		empProduce.send(createDTO(oldEmp,DataRecordState.UPDATED));
		return empRep.save(oldEmp);
	}
	
	public Employee getEmployee(int id) {
		Optional<Employee> empOpt=empRep.findById(id);
		if(empOpt.isEmpty()) {
			throw new DataNotFoundException("id="+id);
		}
		return empOpt.get();
	}

	public List<Employee> getActiveEmployees() {
		
		return empRep.findByTerminated(false);
	}

	public List<Employee> getTerminatedEmployees() {
		return empRep.findByTerminated(true);	}


	public void deleteEmployee(int id) {
		Employee oldEmp=getEmployee(id);
		oldEmp.setTerminated(true);
		empRep.save(oldEmp);
		empProduce.send(createDTO(oldEmp,DataRecordState.DELETED));
		
	}
	
}
