import java.util.ArrayList;

public class Bank {

ArrayList<Customer> customers = new ArrayList<Customer>();
	

	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customers.add(customer);
		
	}


	public Customer getCustomer(int account) {
		// TODO Auto-generated method stub

		return customers.get(account);
	}
	
	ArrayList<Customer>getCustomers()
	{
		return customers;
	}


}
