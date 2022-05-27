package com.digi.employee.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digi.employee.exception.DataNotFoundException;
import com.digi.employee.model.Employee;
import com.digi.employee.repository.EmployeeRepository;
import com.digi.employee.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	@Autowired
	EmployeeService empService;

	@PostMapping(path="/")
	public ResponseEntity<Employee> saveEmployee(@RequestBody @DateTimeFormat(pattern="dd-MM-yyyy") Employee emp){
		Employee savedEmp=empService.saveEmployee(emp);
		
		return new ResponseEntity<>(savedEmp,HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
		try {
			return new ResponseEntity<>( empService.getEmployee(id),HttpStatus.OK);
		}catch(DataNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable int id,@RequestBody Employee emp) {
		try {
			Employee updatedEmp=empService.updateEmployee(id,emp);
			return new ResponseEntity<>(updatedEmp, HttpStatus.OK);
		}catch(DataNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path="/")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return new ResponseEntity<>(empService.getActiveEmployees(),HttpStatus.OK);
	}
	
	@GetMapping(path="/terminated")
	public ResponseEntity<List<Employee>> getTerminatedEmployees(){
		return new ResponseEntity<>(empService.getTerminatedEmployees(),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable int id) {
		try {
			empService.deleteEmployee(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(DataNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
