package com.digi.leaves.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	@Id
	private int id;
	private String name;
	@NotNull
	@Column(name = "active")
	private Boolean terminated = false;
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<Leave> leaves;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, @NotNull Boolean terminated) {
		super();
		this.id = id;
		this.name = name;
		this.terminated = terminated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getTerminated() {
		return terminated;
	}

	public void setTerminated(Boolean terminated) {
		this.terminated = terminated;
	}

	public List<Leave> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Leave> leaves) {
		this.leaves = leaves;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, terminated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(terminated, other.terminated);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", terminated=" + terminated + "]";
	}

}
