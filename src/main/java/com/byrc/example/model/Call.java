package com.byrc.example.model;

import java.util.UUID;

public class Call {

	private UUID callId;
	private Employee employee;
	private int callDuration = 0;
	private User user;

	public Call(final User user) {
		super();
		this.callId = UUID.randomUUID();
		this.user = user;

	}

	public UUID getCallId() {
		return callId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setCallDuration(int callDuration) {
		this.callDuration = callDuration;
	}

	public int getCallDuration() {
		return callDuration;
	}

	@Override
	public String toString() {
		return "Call [callId=" + callId + ", employee=" + employee + ", callDuration=" + callDuration + ", user=" + user
				+ "]";
	}
	
	

}
