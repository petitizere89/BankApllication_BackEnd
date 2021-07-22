package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;
import com.example.utils.ConnectionUtil;
import java.util.Scanner;

public class CheckingDaoDB implements checkingDoa{
	
	private ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	Scanner scan = new Scanner(System.in);
	
	public void createCheckingAccount(Account a) {
				// TODO Auto-generated method stub
		try {
			Connection con = conUtil.getConnection();
			//To use our functions/procedure we need to turn off autocommit
			con.setAutoCommit(false);
			String checking = "insert into checkings( owner_id, balance) values (?,?)";
			CallableStatement chaccount = con.prepareCall(checking);

			chaccount.setInt(1,a.getOwner_id() );
			chaccount.setDouble(2, a.getBalance());
			chaccount.execute();
			
			con.setAutoCommit(true);
			
		} catch(SQLException e) {
			
			System.out.println("In CheckingDaoDB Exception");
			e.printStackTrace();
		}
}

	public List<AccountDisplay> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getUsersPost(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
