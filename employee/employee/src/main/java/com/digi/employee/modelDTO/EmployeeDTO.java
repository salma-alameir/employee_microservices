package com.digi.employee.modelDTO;

import java.util.Date;

import com.digi.employee.model.Employee;

public class EmployeeDTO {

	private DataRecordState dataState;
	private String name;
	private Date timestamp;
	private Integer id;

	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDTO(DataRecordState dataState, String name, Date timestamp, Integer id) {
		super();
		this.dataState = dataState;
		this.name = name;
		this.timestamp = timestamp;
		this.id = id;
	}

	public DataRecordState getDataState() {
		return dataState;
	}

	public void setDataState(DataRecordState dataState) {
		this.dataState = dataState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
