package BankingSystemFinal;
/**
 * @author Manduro I., Paredes J.
 * Section: A
 * This class contains the attributes of the admin
 */

import java.io.Serializable;
public class Admin implements Serializable{

	String Name;
	int AdminID;
	String AdminAddress;
	String AdminContactNumber;
	int AdminPin;

	public Admin (String n, int an, String address, String contactNum, int pin){
		
		Name = n;
		AdminID = an;
		AdminAddress = address;
		AdminContactNumber = contactNum;
		AdminPin = pin;
	}
}

