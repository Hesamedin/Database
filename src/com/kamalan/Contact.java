package com.kamalan;

public class Contact {

	private long id;
	private String name;
	private String phoneNumber;
	
	public long getId() {
		return id;
	}
	
	public String getIdInString() {
		return Long.toString(id);
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	// Will be used by the ArrayAdapter in the ListView
//	@Override
//	public String toString() {
//		return id + "@" + name + "@@" + phoneNumber;
//	}
}
