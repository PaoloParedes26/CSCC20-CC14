package BankingSystemFinal;

/** 
* @author Manduro I., Paredes J
* Section: A
* Contains Printers of the menus and list of actions
*/

public class CallMenu {

/*
 *Method Login outputs the login options for the user
 */
public static void Login () {
	System.out.println();
	System.out.println("	---------------------------------------------------");
	System.out.println("	|	 Welcome! Please choose an option 	  |");
	System.out.println("	---------------------------------------------------");
	System.out.println("	|	 [1]  Close Program                    	  |");
	System.out.println("	|	 [2]  Log in as Member               	  |");
    System.out.println("	|	 [3]  Log in as Admin                  	  |");   
	System.out.println("	---------------------------------------------------");
}

/*
 *Method ClientActions outputs the client's activity options
 */
public static void ClientActions() {
	
	System.out.println();
	System.out.println("	---------------------------------------------------");
	System.out.println("	|	           Member Menu                    |");
	System.out.println("	---------------------------------------------------");
	System.out.println("	|	 [1]  Deposit                             |");
	System.out.println("	|	 [2]  Withdraw                            |");
	System.out.println("	|	 [3]  Transfer Funds                      |");
	System.out.println("	|	 [4]  Inquire Balance                     |");
	System.out.println("	|	 [5]  Log out                             |");
	System.out.println("	---------------------------------------------------");
}

/*
 *Method AdminActions outputs the choices for the admin
 */
public static void AdminActions() {
	System.out.println();
	System.out.println("	---------------------------------------------------");
	System.out.println("	|	           Admin Menu                     |");
	System.out.println("	---------------------------------------------------");	
	System.out.println("	|	 [1]  Open Account                    	  |");
	System.out.println("	|	 [2]  Close Account                       |");
	System.out.println("	|	 [3]  Transfer Funds                      |");
	System.out.println("	|	 [4]  Show List of Accounts               |");
	System.out.println("	|	 [5]  Search for an Account               |");
	System.out.println("	|	 [6]  Show Balance of Account             |");
	System.out.println("	|	 [7]  Show Name of Account Owner          |");
	System.out.println("	|	 [8]  Add Admin                           |");
	System.out.println("	|	 [9]  Delete Admins                       |");
	System.out.println("	|	 [10] Show Admins                         |");
	System.out.println("	|	 [11] Back                                |");
	System.out.println("	---------------------------------------------------");	
}
}
