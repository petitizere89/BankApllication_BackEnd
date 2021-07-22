package com.example.services;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.FileIO;
import com.example.dao.checkingDoa;
import com.example.dao.AccountDao;
import com.example.logging.Logging;
import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;
import java.util.Scanner;

public class AccountService {
	
	double cbalance = 0D;
	double sbalance = 0D;
	Scanner scan = new Scanner(System.in);
	
	private AccountDao aDao;
	private checkingDoa cDao; 
	
	public AccountService(AccountDao a) {
		this.aDao = a;
	}
	//	aServ.addAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail());
	public void addAccount(int id, String firstName, String lastName, String email) {
		
		System.out.println("Please enter initial deposit");
		
		double money = Integer.parseInt(scan.nextLine());
		
		
		Account p = new Account(id,id, firstName, lastName, email, money); // we are inserting into database id and money.
		aDao.createAccount(p);
		
	}
	public void addCheckingAccount(int id, String firstName, String lastName, String email,double deposit) throws SQLException {
		
//		System.out.println("Give me checking monye");
//		
//		double money = Integer.parseInt(scan.nextLine());
		cbalance = cbalance+ deposit;

		Account p = new Account(id,id, firstName, lastName, email, cbalance); // we are inserting into database id and money.

		aDao.createCheckingAccount(p);
		
	}
	
	public void addSavingAccount(int id, String firstName, String lastName, String email, double deposit) {
//		
//		System.out.println("Give me saving monye");
//		
//		double money = Integer.parseInt(scan.nextLine());
	
		sbalance += deposit;
		Account p = new Account(id,id, firstName, lastName, email, sbalance); // we are inserting into database id and money.

		aDao.createSavingAccount(p);
		
	}
	
	
	public List<AccountDisplay> getAllAccounts(){
		return aDao.getAllAccounts();
	}
	
	public User loadUserPosts(User u) {
		return aDao.getUsersPosts(u);
	}
	
	

	
}
