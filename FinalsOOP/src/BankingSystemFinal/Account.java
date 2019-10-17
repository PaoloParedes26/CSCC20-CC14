package BankingSystemFinal;

/**
 * @author Manduro I., Paredes J.
 * Section: A
 * This class contains the attributes of an account
 */

//Account class
import java.io.Serializable;
public class Account implements Serializable{

	String Name;
	int AccNum;
	float Balance;
	String Address;
	String ContactNumber;
	int CustomerID;
	int Pin;
	int AdminID;

	public Account (String n, int an, float b, String address, String contactNum, int custID, int pin, int addAdminID){
		
		Name = n;
		AccNum = an;
		Balance = b;
		Address = address;
		ContactNumber = contactNum;
		CustomerID = custID;
		Pin = pin;
		AdminID = addAdminID;
	}
}

