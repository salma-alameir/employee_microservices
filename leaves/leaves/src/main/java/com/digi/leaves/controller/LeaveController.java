package com.digi.leaves.controller;

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

import com.digi.leaves.exception.DataNotFoundException;
import com.digi.leaves.model.Employee;
import com.digi.leaves.model.Leave;
import com.digi.leaves.modelDTO.LeaveDTO;
import com.digi.leaves.service.EmployeeService;
import com.digi.leaves.service.LeaveService;


@RestController
@RequestMapping("/leave")
public class LeaveController {

	
	@Autowired
	LeaveService leaveService;
	
	@Autowired
	EmployeeService empService;

	@PostMapping(path="")
	public ResponseEntity<Leave> saveLeave(@RequestBody @DateTimeFormat(pattern="dd-MM-yyyy") LeaveDTO leave){
		Leave savedLeave=leaveService.saveLeave(leave);
		
		return new ResponseEntity<>(savedLeave,HttpStatus.CREATED);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Leave> getLeave(@PathVariable int id) {
		try {
			return new ResponseEntity<>( leaveService.getLeave(id),HttpStatus.OK);
		}catch(DataNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Object> updateLeave(@PathVariable int id,@RequestBody Leave leave) {
		try {
			Leave updatedLeave=leaveService.updateLeave(id,leave);
			return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
		}catch(DataNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path="")
	public ResponseEntity<List<Leave>> getAllLeaves(){
		return new ResponseEntity<>(leaveService.getLeaves(),HttpStatus.OK);
	}
	
	@GetMapping(path="/employee/{id}")
	public ResponseEntity<Employee> getEmployeeLeaves(@PathVariable int id){
		return new ResponseEntity<>(empService.getEmployeeLeaves(id),HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteLeave(@PathVariable int id) {
		try {
			leaveService.deleteLeave(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(DataNotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
