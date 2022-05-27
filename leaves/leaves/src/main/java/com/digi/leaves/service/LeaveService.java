package com.digi.leaves.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digi.leaves.exception.DataNotFoundException;
import com.digi.leaves.model.Employee;
import com.digi.leaves.model.Leave;
import com.digi.leaves.modelDTO.LeaveDTO;
import com.digi.leaves.repository.LeaveRepository;

@Service
public class LeaveService {

	@Autowired
	LeaveRepository leaveRep;
	
	@Autowired
	EmployeeService empService;
	
	public Leave saveLeave(LeaveDTO leaveDTO) {
		Employee emp =empService.getEmployee(leaveDTO.getEmployee());
		Leave leave=new Leave(emp,leaveDTO.getReason(),leaveDTO.getLeaveDate());
		return leaveRep.save(leave);
	}

	public Leave getLeave(int id) {
		Optional<Leave> optLeave=leaveRep.findById(id);
		if(optLeave.isEmpty()) {
			throw new DataNotFoundException("id="+id);
		}else {
			return optLeave.get();
		}
	}

	public Leave updateLeave(int id, Leave leave) {
		Leave oldLeave=getLeave(id);
		oldLeave.setReason(leave.getReason());
		oldLeave.setLeaveDate(leave.getLeaveDate());
		return leaveRep.save(oldLeave);
	}

	public List<Leave> getLeaves() {
		return leaveRep.findAll();
	}

	public void deleteLeave(int id) {
		Leave oldLeave=getLeave(id);
		leaveRep.delete(oldLeave);
		
	}
	
	

}
