package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;

public interface AccountDao {
	
	public void createAccount(Account p);
	public void createCheckingAccount(Account ac) throws SQLException;
	public void createSavingAccount(Account as);
	
	public List<AccountDisplay> getAllAccounts();
	
	public User getUsersPosts(User u);


	

	
}
