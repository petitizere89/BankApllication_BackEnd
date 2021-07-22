package com.example.dao;

import java.util.List;

import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;

public interface checkingDoa {

	public  void createCheckingAccount(Account a);
	public List<AccountDisplay> getAllAccounts();
	
	public User getUsersPost(User u);
	
}
