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
import java.util.Scanner;

import com.example.dao.AccountDao;
import com.example.dao.AccountDaoDB;
import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.exceptions.InvalidCredentialsException;
import com.example.models.Account;
import com.example.models.AccountDisplay;
import com.example.models.User;
import com.example.services.AccountService;
import com.example.services.UserService;
import com.example.utils.*;

public class SocialHubDriver {
	
	private static UserDao uDao = new UserDaoDB();
	private static AccountDao pDao = new AccountDaoDB();
	private static UserService uServ = new UserService(uDao);
	private static AccountService aServ = new AccountService(pDao);
	private static ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	String accountType = "";
	
	public static void main(String[] args) throws SQLException {
		
		
		Scanner in = new Scanner(System.in);
		Menu menu = new Menu();
		//This will be used to control our loop
		boolean done = false;
		
		User u = null;
		
		User employee = new User("Joel", "Bennett","bjoel@yahoo.com","password");
		
		
		while(!done) {
			if(u == null) {
			   
			   menu.printHeader();
			   
				int choice = Integer.parseInt(in.nextLine());
				if(choice == 1) {
					System.out.print("Please enter your username: ");
					String username = in.nextLine();
					System.out.print("Please enter your password: ");
					String password = in.nextLine();
					try {
						u = uServ.signIn(username, password);
						System.out.println("Welcome " + u.getFirstName());
					} catch(Exception e) {
						System.out.println("Username or password was incorect. Goodbye");
						done = true;
					}
				} else {
					System.out.print("Please enter you first name: ");
					String first = in.nextLine();
					System.out.print("Please enter your last name: ");
					String last = in.nextLine();
					System.out.print("Please enter your email: ");
					String email = in.nextLine();
					System.out.print("Please enter a password: ");
					String password = in.nextLine();
					try {
						u = uServ.signUp(first, last, email, password);
					
						System.out.println(employee);
						// if this username does not exist in the database, then 
						//print, "Waiting for aproval from our member service specialist
						//then parse in the username to the employee for aproval
						//else , continue with the bellow statement.
						System.out.println(u.getUsername());
						System.out.println("You may now log in with the username: " + u.getUsername());
					} catch (Exception e) {
						System.out.println("Sorry, we could not process your request");
						System.out.println("Please try again later");
						done = true;
					}
					
					if(!done) {
						System.out.print("Please enter your username: ");
						String username = in.nextLine();
						System.out.print("Please enter your password: ");
						 password = in.nextLine();
						try {
							u = uServ.signIn(username, password);
							System.out.println("Welcome " + u.getFirstName() +" " + u.getLastName() +" t");
						} catch(Exception e) {
							System.out.println("Username or password was incorect. Goodbye");
							done = true;
						}
					}
				}
				
			} else 
			{


				
				
				menu.printMenu();
				//System.out.println("To view posts press 1, to create a post press 2");
				menu.performAction(menu.getInput());
				System.out.println(menu.choice);
				
				
		     if(menu.choice==1) {
					if( menu.accType.equalsIgnoreCase("checking")==true)
					{
						
						//System.out.println("in the checking if statement Inital");
						aServ.addCheckingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),menu.initDeposit);
					}
					else if(menu.accType.equalsIgnoreCase("saving")==true) 
					{
						//System.out.println("in the saving if statement Inital");
						aServ.addSavingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),menu.initDeposit);
					}
					else
					{
						//System.out.println("in the account else statement Inital");
	
						aServ.addAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail());
					}
		     	}
		     else if(menu.choice == 4)
		     {
					
					System.out.println("From checking or saving?");
					String location = in.nextLine();
					
					if( location.equalsIgnoreCase("checking")==true)
					{
					
					  Connection m_Connection =conUtil.getConnection();
					  
					  Statement m_Statement = m_Connection.createStatement();
					  String query = "SELECT ID,owner_id,balance FROM checkings WHERE ID<100";

					  ResultSet m_ResultSet = m_Statement.executeQuery(query);

					  while (m_ResultSet.next()) {
						    System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2) + ", "
						                       + m_ResultSet.getString(3)+ "" );
				      }
					}
		     }
		     
		    else
		    {
		    	
//		    	if( menu.accType.equalsIgnoreCase("checking")==true)
//				{
//					
//					System.out.println("in the checking if statement");
//					aServ.addCheckingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),menu.amount);
//				}
//				else if(menu.accType.equalsIgnoreCase("saving")==true) 
//				{
//					System.out.println("in the saving if statement");
//					aServ.addSavingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),menu.amount);
//				}
//				else
//				{
		
					if(menu.choice == 2)
					{
						System.out.println("Where would you like to deposit the money?");
						String accountDeposit = in.nextLine();
						if(accountDeposit.equalsIgnoreCase("checking"))
						{
							//System.out.println("in the checking if statement else");
							aServ.addCheckingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),menu.amount);
						}
						else if(accountDeposit.equalsIgnoreCase("saving"))
						{
							System.out.println("in the saving if statement else");
							aServ.addSavingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),menu.amount);
						}
						else
						{
							aServ.addAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail());
						}
					}
					if(menu.choice == 3)
					{
						System.out.println("Where would you like to withdrwal the money?");
						String accountDeposit = in.nextLine();
						if(accountDeposit.equalsIgnoreCase("checking"))
						{
							//System.out.println("in the checking if statement else");
							aServ.addCheckingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),-menu.amount);
						}
						else if(accountDeposit.equalsIgnoreCase("saving"))
						{
							//System.out.println("in the saving if statement else");
							aServ.addSavingAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail(),-menu.amount);
						}
						else
						{
							aServ.addAccount(u.getId(), u.getFirstName(),u.getLastName(),u.getEmail());
						}
					}
				//}
		    	
		    }
		     
				/*
				int choice = Integer.parseInt(in.nextLine());
				//If the user chooses 1, we will show them the list of posts
				if(choice == 1) {
					List<AccountDisplay>accounts = pServ.getAllAccounts();
					for(AccountDisplay account: accounts) {
						System.out.println(account.getUsername() + ":");
						System.out.println(account.getContent());
						System.out.println();
					}
					System.out.println("Are you finished? Press 1 for yes, press 2 for no");
					choice = Integer.parseInt(in.nextLine());
					done = (choice == 1) ? true : false;
				} else {
					System.out.println("Please enter your content below:");
					String content = in.nextLine();
					pServ.addPost(u.getId(), u.getId(), content);
					System.out.println("Post was received, are you finished? Press 1 for yes, press 2 for no");
					choice = Integer.parseInt(in.nextLine());
					done = (choice == 1) ? true : false;
				}
				*/
			}
		}
		
		System.out.println("Goodbye :)");
		in.close();
		
	}


	
}
