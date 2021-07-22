package com.example.models;

import java.io.Serializable;

public class Account{
	
	private int id;
	private int owner_id;
	private String firstName;
	private String lastName;
	private String email;
	
	private double balance;
	
	public Account() {
		
	}
	
	public Account(int id, int owner_id, String firstName, String lastName, String email,  double balance) {
		this.id = id;
		this.owner_id = owner_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		
		this.balance = balance;
	}
	
	public Account(int owner_id, double balance) {
		this.owner_id = owner_id;
		
		this.balance = balance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	

	@Override
	public String toString() {
		return "Account [id=" + id + ", owner_id=" + owner_id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", balance=" + balance + "]";
	}





	
	
	
	
	
}
