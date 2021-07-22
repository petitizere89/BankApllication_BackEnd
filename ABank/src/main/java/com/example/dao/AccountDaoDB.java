package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;
import com.example.utils.ConnectionUtil;
import java.util.Scanner;

public class AccountDaoDB implements AccountDao{
	
	private ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	Scanner scan = new Scanner(System.in);
	
	//We use callable statements to call stored procedures and functions from java
	@Override
	public void createAccount(Account p) {
		
		try {
			Connection con = conUtil.getConnection();
			//To use our functions/procedure we need to turn off autocommit
			con.setAutoCommit(false);
			
			String deleteFromTable = "DELETE FROM accounts WHERE owner_id='8'";
	        try (Connection conn = conUtil.getConnection();
	                PreparedStatement pstmt = conn.prepareStatement(deleteFromTable)) {

	            // set the corresponding param
	            pstmt.setInt(1, p.getOwner_id());
	            // execute the delete statement
	            pstmt.executeUpdate();

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
			
			String sql = "insert into accounts( owner_id, balance ) values (?,?)";
			CallableStatement cs = con.prepareCall(sql);
			
			cs.setInt(1, p.getOwner_id());
			cs.setDouble(2, p.getBalance());
			
			cs.execute();
			
			con.setAutoCommit(true);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void createCheckingAccount(Account ac) throws SQLException {
		// TODO Auto-generated method stub

//		String deleteFromTable = "DELETE FROM checkings WHERE owner_id='8'";
//        try (Connection conn = conUtil.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(deleteFromTable)) {
//
//            // set the corresponding param
//            pstmt.setInt(1, ac.getOwner_id());
//            // execute the delete statement
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        
		try {
			Connection con = conUtil.getConnection();
			//To use our functions/procedure we need to turn off autocommit
     		con.setAutoCommit(false);
//			String deleteFromTable = "DELETE FROM checkings WHERE owner_id='8'";
//			CallableStatement delchaccount = con.prepareCall(deleteFromTable);
//			 delchaccount.setInt(1,  ac.getOwner_id());
//			 
			
			String checking = "insert into checkings( owner_id, balance) values (?,?)";

			CallableStatement chaccount = con.prepareCall(checking);
			
		
			chaccount.setInt(1,ac.getOwner_id() );
			chaccount.setDouble(2, ac.getBalance());
			chaccount.execute();
			
			con.setAutoCommit(true);
			
		} catch(SQLException e) {
			
			System.out.println("In CheckingDaoDB Exception");
			e.printStackTrace();
		}
		
			  
		
		
}
	
	@Override
	public void createSavingAccount(Account as) {
		// TODO Auto-generated method stub
		try {
			Connection con = conUtil.getConnection();
			//To use our functions/procedure we need to turn off autocommit
			con.setAutoCommit(false);
			String saving = "insert into savings( owner_id, balance) values (?,?)";
			CallableStatement chaccount = con.prepareCall(saving);
		
			chaccount.setInt(1,as.getOwner_id() );
			chaccount.setDouble(2, as.getBalance());
			chaccount.execute();
			
			con.setAutoCommit(true);
			
		} catch(SQLException e) {
			
			System.out.println("In CheckingDaoDB Exception");
			e.printStackTrace();
		}
}

	@Override
	public List<AccountDisplay> getAllAccounts() {
		
		List<AccountDisplay> pList = new ArrayList<AccountDisplay>();
		
		try {
			Connection con = conUtil.getConnection();
			con.setAutoCommit(false);
			//Use this syntax to call a stored function
			String sql = "{?=call get_all_posts()}";
			
			CallableStatement cs = con.prepareCall(sql);
			
			cs.registerOutParameter(1, Types.OTHER);
			
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				AccountDisplay post = new AccountDisplay(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				pList.add(post);
			}
			
			con.setAutoCommit(true);
			return pList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getUsersPosts(User u) {
		List<Account> posts = new ArrayList<Account>();
		try {
			Connection con = conUtil.getConnection();
			con.setAutoCommit(false);
			String sql = "{?=call get_user_posts(?)}";
			
			CallableStatement cs = con.prepareCall(sql);
			
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, u.getId());
			
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
			//	Account p = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
				Account p = new Account(rs.getInt(1), rs.getInt(2));
				posts.add(p);
			}
			
			u.setPosts(posts);
			
			con.setAutoCommit(true);
			return u;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
