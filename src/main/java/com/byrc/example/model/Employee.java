package com.byrc.example.model;

import java.util.UUID;

public  abstract class Employee {
	private UUID employeeId;
	private String employeeName;

	public Employee() {
		this.employeeId = UUID.randomUUID();
		this.employeeName = "Empl_" + this.employeeId.toString();

	}

	public UUID getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UUID employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + "]";
	}
	
	

}
