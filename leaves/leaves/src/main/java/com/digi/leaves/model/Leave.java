package com.digi.leaves.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.digi.leaves.util.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name="leaves")
public class Leave {

	@Id
	@GeneratedValue
	private int id;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_leave_employee"))
	private Employee employee;
	private String reason;
	@Column(name = "leave_date")
	@JsonDeserialize(using = DateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY")
	private Date leaveDate;
	
	public Leave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Leave(Employee employee, String reason, Date leaveDate) {
		super();
		this.employee = employee;
		this.reason = reason;
		this.leaveDate = leaveDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employee, id, leaveDate, reason);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Leave other = (Leave) obj;
		return Objects.equals(employee, other.employee) && id == other.id && Objects.equals(leaveDate, other.leaveDate)
				&& Objects.equals(reason, other.reason);
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", employee=" + employee + ", reason=" + reason + ", leaveDate=" + leaveDate + "]";
	}
	
	
	
	
	
}
