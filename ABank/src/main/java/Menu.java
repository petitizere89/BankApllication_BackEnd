import java.util.Scanner;

import com.example.models.Account;
import com.example.services.AccountService;

import java.util.ArrayList;
import com.example.dao.*;



public class Menu {
	Scanner scan = new Scanner (System.in);
    Bank bank = new Bank();
    String accType ="";
    
	
	private AccountDao aDao;
	private checkingDoa cDao; 
	
    double initDeposit = 0D;
	double amount = 0D;
	int choice = -1;
	
	 public void printHeader() {
		// TODO Auto-generated method stub
		
		System.out.println("+------------------------------------------------------+");
		System.out.println(":                                                      :");
		System.out.println(":                Welcome to Bank of Izere              :");
		System.out.println(":                                                      :");
		System.out.println(":  A unique banking experience that meets all your     :"
					   + "\n: requirements and guarantees a personalised service.  : ");
		System.out.println("+------------------------------------------------------+");
		System.out.println(":          Press 1 to Login, Press 2 to Signup         :");
		System.out.println("+------------------------------------------------------+");
	}

	
	
	public void printMenu() {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("1: Create a new account");
		System.out.println("2: Make a deposite" );
		System.out.println("3: Make a widthdrwal");
		System.out.println("4: List account balance");
		System.out.println("0: EXIT");
	}

	public int getInput() {
		// TODO Auto-generated method stub
		do {
			try 
			{
				choice = Integer.parseInt(scan.nextLine());
				
			}catch(NumberFormatException e) 
			{
				System.out.println("Invalid outside of range. Please chose again.");
			}
			if(choice <0 || choice > 4)
			{
				System.out.println("Choice outside of range. Please chose again");
			}
		}while(choice <0 || choice > 4);
		
		return choice;
	}
	
	
	public void performAction(int choice) {
		// TODO Auto-generated method stub
		
		switch(choice)
		{
		case 0:
			
			System.out.println("Thank you for using our application.");
			System.exit(0);;
			break;
		case 1:
			createAnAccount();
			break;
		case 2:
			MakeADeposit();
			break;
			
		case 3:
			MakeAWidthrawl();
			break;
			
		case 4:
			listBalances();
			break;
		case 5:
				default:
				  System.out.println("Unkown error has occured.");
		
		}
		
	}
	private void listBalances()
	{
	

	}
	
	private void MakeADeposit() {
		// TODO Auto-generated method stub
			System.out.println("How much would you like to deposite?: ");

			
			try
			{
				amount = Double.parseDouble(scan.nextLine());
			}
			catch(NumberFormatException e)
			{
				amount = 0;
			}
			deposit(amount);
		
	}

	private void MakeAWidthrawl() {
		// TODO Auto-generated method stub
	//	int account = selectAccount();
		//if(account >= 0)
		//{
			System.out.println("How much would you like to withdraw?: ");
			double amount = 0;
			
			try
			{
				amount = Double.parseDouble(scan.nextLine());
			}
			catch(NumberFormatException e)
			{
				amount = 0;
			}
			withdraw(amount);

		//}
		
	}
	
	public void withdraw(double amount)
	{ 
		double balance = initDeposit;
		if(balance<=0)
		{
			System.out.println("You hav unsuficient funds");
			return;
		}
	//	balance -= amount;
		System.out.println("You have withdrawn " + amount );
		//System.out.println("You now have a balance of $ " + balance);
	
	}	
	
	
	public void deposit(double amount)
	{
		if(amount <= 0)
		{
			System.out.println("You ca not deposit negative money");
			return;
		}

		System.out.println("You have deposited "+ amount );
		//System.out.println("You now have a balance of $ " + balance);
		
	}
	
	
//	private int selectAccount() {
//		// TODO Auto-generated method stubArrayList<Customer>customer = bank.getCustomers();
//		ArrayList<Customer> customers = bank.getCustomers();
//		
//		if(aDao.getAllAccounts().isEmpty())
//		{
//			System.out.println("There are no customers in your bank");
//			return -1;
//		}
//		
//		
//		System.out.println("Select an accountL ");
//		
//		for (int i =0; i <customers.size(); i++)
//		{
//			System.out.println((i+1) + ") " + customers.get(i).basicInfo());
//			
//		}
//		int account = 0;
//		System.out.println("Please enter your selection: ");
//		try
//		{
//			account = Integer.parseInt(scan.nextLine())-1;
//		}
//		catch(NumberFormatException e)
//		{
//			account = -1;
//		}
//		if(account < 0 || account > customers.size())
//		{
//			System.out.println("Invalid account selected");
//			account = -1;
//		}
//
//
//		return account;
//	}


	public String createAnAccount() {
//		AccountService accountSer = new AccountService();
//		accountSer.addAccount();
//		 
	
		String firstName = " ", lastName = "",ssn, accountType = "";
		double initialDeposit = 0D;
		boolean valid = false;
		
		while (!valid)
		{
			System.out.println("Please enter an account type");
			accountType = scan.nextLine();
			getAccountType(accountType);
			if(accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("saving"))
			{
				valid = true;
			}
			else
			{
				System.out.println("Invalid account type selected. Please enter checking or savings.");
			}
			
			
		}
		System.out.println("Please enter your first name");
		firstName = scan.nextLine();
		System.out.println("Please enter your last name");
		lastName = scan.nextLine();;

		
		valid = false;
		
		while(!valid)
		{
				System.out.println("Please enter an inital deposit: ");
			
			
			try
			{
				initialDeposit = Double.parseDouble(scan.nextLine());
			}
			catch(NumberFormatException e)
			{
				System.out.println(" Deposit must be a number: ");
			}
			
			if(accountType.equalsIgnoreCase("checking")) 
			{
				if(initialDeposit < 100)
				{
					System.out.println("CHECKING ACCOUNTS REQUIRE A MINIMUM OF $100 TO OPEN");
				}
				else
				{
					valid = true;
				}
			}
			else if(accountType.equalsIgnoreCase("saving"))
			{
				if(initialDeposit < 50)
				{
					System.out.println("SAVING ACCOUNTS REQUIRE A MINIMUM OF $50 TO OPEN");
	
				}
				else
				{
					valid = true;
				}
			}
			getInitDeposit(initialDeposit);
	
		}
		Account account;
		if(accountType.equalsIgnoreCase("checking"))
		{
			account = new Checking(initialDeposit);
			
		}
		else
		{
			account = new Savings(initialDeposit);
		}
		
		Customer customer = new Customer( firstName, lastName, account); // creating a customer
		bank.addCustomer(customer);
		
	 return accountType;
	}



	public void getAccountType(String accountType) {
		// TODO Auto-generated method stub
	 this.accType =	accountType;

	}
	public void getInitDeposit(double initDeposit) {
		// TODO Auto-generated method stub
	 this.initDeposit = initDeposit;
	}



    



}
