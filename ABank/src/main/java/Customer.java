import com.example.models.Account;

public class Customer {


	private String firstName;
	private String lastName;
	private Account account;
	private String ssn;

	public Customer(String firstName, String lastName, Account account) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.account = account;
	}

	@Override
	public String toString() {
		return "\nCustomer information\n"
				+"firstName=" + firstName +"\n"
				+ ", lastName=" + lastName +"\n"
				+ ", account=" + account +"\n"
				+ ", ssn=" + ssn + "]";
	}
	

	public String basicInfo() {
		return "firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", ssn=" + ssn + ""
				+ ", account number=" + 100000 ;
	}
	
	
	Account getAccount()
	{
		return account;
	}
	

}
