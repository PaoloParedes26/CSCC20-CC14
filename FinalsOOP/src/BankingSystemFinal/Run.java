package BankingSystemFinal;

/**
 * @author Manduro I., Paredes J.
 * Section: A
 * Description: This class contains the reading and writing of a file and the run program
 */

import java.util.*;
import java.io.*;
import java.text.*;
import java.sql.*;

class Run implements Serializable {
	
	/**
	  * Constructor
	  */
	
	int trans = 0;
	
	public Run(){

	}

	/**
	  * method ReadFromFile - reads or loads the linked list with the accounts saved by the system
	  * @return o_userdata - LinkedList objects containing the Accounts and its data
	  */
	private static AccountList readFromFile(){
		AccountList o_userdata = new AccountList();
		try {
	    	FileInputStream fileInput = new  FileInputStream("list.txt");
		    ObjectInputStream objInput = new ObjectInputStream(fileInput);
		    Object obj = objInput.readObject();
		    o_userdata = (AccountList) obj;
		} 
		catch (Exception e) {

		} 
		return o_userdata;
	}

	/**
	  * method ReadFromFile - reads or loads the linked list with the admins saved by the system
	  * @return o_userdata - LinkedList objects containing the Accounts and its data
	  */
	private static AdminList readFromFileAdmin(){
		AdminList o_admindata = new AdminList();
		try {
	    	FileInputStream fileInput = new  FileInputStream("adminlist.txt");
		    ObjectInputStream objInput = new ObjectInputStream(fileInput);
		    Object object = objInput.readObject();
		    o_admindata = (AdminList) object;
		} 
		catch (Exception e) {

		} 
		return o_admindata;
	}

	/**
	 * method WriteToFile - method for Saving List of Accounts
	 * @param Accountlist list - The list of accounts with a linked list structure.
	 * @return void
	 */
	private static void WriteToFile(AccountList list){
		try {
		    FileOutputStream fos = new FileOutputStream ("list.txt");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(list);
		    fos.close();
			} 
		catch (Exception e) {
		    System.out.println(e);   
		}
	}

	/**
	  * method WriteToFile - method for Saving List of Admins
	  * @param AdminList list - The list of admins with a linked list structure.
	  * @return void
	  */
	private static void writeToFileAdmin(AdminList adlist){
		try {
	    	FileOutputStream fileOutput = new FileOutputStream ("adminlist.txt");
	    	ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
	    	objOutput.writeObject(adlist);
	    	fileOutput.close();
	  	} 
	  	catch (Exception e) {
	    	System.out.println(e);   
	  	}
	}

	public void runProgram(){
		Scanner input = new Scanner(System.in);
		DecimalFormat Billion = new DecimalFormat("##0.####"); // Allow Digits more than 100 million
		int search;
		int searchpin;
		String year = "2019"; 
		String action;
		String TransactionID;
		AdminList adlist;
		AccountList list;
		//Loads the List File if it exists
		list = readFromFile();
		adlist = readFromFileAdmin();
		boolean loop = false;
		do{
			clearScreen();
			//Menu to display log in choice 
			CallMenu.Login();													
	  		System.out.print("	Enter Choice: ");
			int choice = input.nextInt();
			
			switch (choice){
				//exit program
				case 1:
					loop = true;
					break;

				//enter as member
				case 2: 
					boolean infFindClient = true;
					boolean foundclient;
					boolean pinfound;
					int pin;
					if(list.isEmpty()) {
						System.out.println();
						System.out.println("	No Accounts in System");
						pause();
					}else
					do {
						System.out.print("	Please Enter PIN of Account: ");
						search = input.nextInt();
						pinfound = list.PinList(search);
						if(pinfound != true) {
							System.out.println("	Incorrect PIN");
							System.out.println();
							pause();
						}
						else{
							System.out.println("\n	ENTERED ACCOUNT FOUND\n");
							pause();
							boolean clientloop2 = true;	
							
							do {
								//Menu to choose a client action
								CallMenu.ClientActions();
								System.out.print("	Enter Choice: ");
								int clientchoice = input.nextInt();
								
								//Performs action based on user choice
								switch (clientchoice) {
								//Deposit
								case 1:
									float value;
									float deposited;
									boolean infDeposit = true;
									do {
										System.out.print("	Please Enter Amount to Deposit: ");
										value = input.nextFloat();

											deposited = list.Deposit(search, value);
											System.out.print("	Successfully Deposited Amount: ");
											System.out.println(Billion.format(deposited));
											alterBalance(search, deposited);
											System.out.println();
											infDeposit = false;
								}while(infDeposit);
								trans++;
								WriteToFile(list);
								TransactionID = year + trans;
								action = "Deposit";
								insertTransaction(TransactionID,action);
								pause();
								break;
									
								//Withdraw
								case 2:
									float fromBal1;
									float value1;
									float withdrawed;
									boolean infWithdraw = true;
									do {
										fromBal1 = list.GetBalance(search);
										System.out.print("	Please Enter Amount to Withdraw: ");
										value1 = input.nextFloat();
										if(fromBal1 > 0 && value1 <= fromBal1) {
											withdrawed = list.Withdraw(search, value1);
											System.out.print("	Successfully Withdrawed Amount: " + Billion.format(withdrawed));
											alterBalance(search,list.GetBalance(search));
											System.out.println();
											infWithdraw = false;
										}else
											System.out.println("	Insufficient Balance in Source Account! ");
											System.out.println();
											infWithdraw = false;
									}while(infWithdraw);
									trans++;
									WriteToFile(list);
									TransactionID = year + trans;
									action = "Withdraw";
									insertTransaction(TransactionID,action);
									
									pause();
									break;
									
								//Fund Transfer
								case 3:
									int ClientFund = search;
									boolean infTransferFunds = true;
									boolean to   = false;
									int toID     = 0;
									float amount;
									float fromBal2;
									do {
											System.out.print("	Please Enter ID of Fund Destination Account: ");
											search = input.nextInt();
											to = list.InList(search);
											if(to != true) {
												System.out.println("	Entered Account Does Not Exist");
												System.out.println();
											}else {
												toID = search;
												fromBal2 = list.GetBalance(ClientFund);
												System.out.print("	Please Enter Amount to Transfer: ");
												amount = input.nextInt();
												if(fromBal2 > 0 && amount <= fromBal2) {
													list.Transfer(ClientFund, toID, amount);
													System.out.println("	Fund Transfer Successful! ");
													System.out.println();
												}else
													System.out.println("	Insufficient Balance in Source Account! ");
													System.out.println();
											}
									infTransferFunds = false;
									}while (infTransferFunds);
									trans++;
									WriteToFile(list);
									TransactionID = year + trans;
									action = "Fund Transfer";
									insertTransaction(TransactionID,action);
									search = ClientFund;
									pause();
									break;
									
								//Inquire Balance
								case 4:									
									boolean infGetClientBalance = true;
									float ShowBal = 0;
									do {
											ShowBal = list.GetBalance(search);
											System.out.println("	Balance of Your Account is "+ Billion.format(ShowBal));
											System.out.println();
											infGetClientBalance = false;
										}while(infGetClientBalance);
									pause();
									break;
								
								//Go Back
								case 5:									
									clientloop2 = false;
									break;
								default:
									System.out.println("	Enter a valid choice.");	
									pause();
								}
							}while(clientloop2);
						break;
						}
						infFindClient = false;	
					}while(infFindClient);
				break;
					
				//enter as admin
				case 3: 
					boolean foundpin;
					boolean loop2 = false;
					System.out.print("	Please Enter PIN of Admin: ");
					search = input.nextInt();
					foundpin = adlist.PinList(search);
					if(foundpin != true){
						System.out.println();
						System.out.println("	Incorrect PIN");
						pause();
					}else
					do{	
						clearScreen();
						//Menu to choose an admin action
						CallMenu.AdminActions();															
						System.out.print("	Enter Choice: ");
						int adminchoice = input.nextInt();

						//Performs action based on user choice
						switch (adminchoice) {
							//Open Account
							case 1: 										
								System.out.println("	Please Fill Up Account information. . .");
								System.out.print("	Enter First Name - Middle Initial. - Last Name: ");
								String addname;
								input.nextLine();
								addname = input.nextLine();
								System.out.print("	Account Number: ");
								int addaccnum = input.nextInt();
								if(list.InList(addaccnum)) {
									System.out.println("	Chosen ID is Already in List");
									System.out.println();
									pause();
									break;
								}
								System.out.print("	Balance: ");
								float addbal = input.nextFloat();
								if(addbal < 0) {
									System.out.println("	Cannot Add Negative Balance!");
									System.out.println();
									pause();
									break;
								}
								System.out.print("	Address: ");
								String addAddress;
								input.nextLine();
								addAddress = input.nextLine();
								System.out.print("	Contact Number: ");
								String addContactNum = input.nextLine();
								System.out.print("	Customer ID: ");
								int addCustID = input.nextInt();
								System.out.print("	Enter Admin ID: ");
								int addAdminID = input.nextInt();
								System.out.print("	PIN: ");
								int addPin = input.nextInt();
								
								Account newacc = new Account(addname, addaccnum, addbal, addAddress, addContactNum, addCustID, addPin, addAdminID);
								
								
								list.AddAccount(newacc);
								System.out.println();
								System.out.println("	Account Opened Successfully!");
								WriteToFile(list);

								insertCustomer(addname,addAddress,addContactNum,addCustID,addaccnum);
								insertAccount(addaccnum, addPin, addbal, addCustID, addAdminID);
								insertAccountBalance(addaccnum,addbal);
								
								pause();
								break;
								
							//Close Account
							case 2:
								boolean infDeleteAccount = true;
								boolean DelExist = false;
								Account result;
								if(list.isEmpty()) {
									System.out.println("	List Is Empty!");
									System.out.println();
								}else
								do {
									System.out.print("	Please Enter ID of Account to Delete/Close: ");
									search = input.nextInt();
									DelExist = list.InList(search);
									if(!DelExist) {
										System.out.println("	Entered Account Does Not Exist");
										System.out.println();
									}else {
										result = list.DeleteAccount(search);
										System.out.println("	Account of "+ result.Name + " has been closed" );
										System.out.println();
									}
									infDeleteAccount = false;
								}while(infDeleteAccount);
								WriteToFile(list);
								pause();
								break;
								
							//Transfer Funds from one account to another
							case 3:
								boolean infTransferFunds = true;
								boolean from = false;
								int fromID   = 0;
								boolean to   = false;
								int toID     = 0;
								float amount;
								float fromBal;
								do {
									System.out.print("	Please Enter ID of Fund Source Account: ");
									search = input.nextInt();
									from = list.InList(search);
									if(from != true) {
										System.out.println("	Entered Account Does Not Exist");
										System.out.println();
									}else {
										fromID = search;
										System.out.print("	Please Enter ID of Fund Destination Account: ");
										search = input.nextInt();
										to = list.InList(search);
										if(to != true) {
											System.out.println("	Entered Account Does Not Exist");
											System.out.println();
										}else {
											toID = search;
											fromBal = list.GetBalance(fromID);
											System.out.print("	Please Enter Amount to Transfer: ");
											System.out.println();
											amount = input.nextInt();
											if(fromBal > 0 && amount <= fromBal) {
												list.Transfer(fromID, toID, amount);
												System.out.println("	Fund Transfer Successful! ");
												System.out.println();
											}else
												System.out.println("	Insufficient Balance in Source Account! ");
												System.out.println();
										}
									}
								infTransferFunds = false;
								}while (infTransferFunds);
								pause();
								break;

							//Open Sort and Print List Options
							case 4:										
								boolean loop3 = false;

								if(list.isEmpty()) {
									System.out.println();
									System.out.println("	There are Currently No Accounts in the System");
									pause();
									break;
								}
								else {	
								//Menu to display choices the desired action for sorting/
								//displaying list of accounts currently in the system							
									list.PrintAll();
									pause();
									break;	
								}

							//Search for Account using Linear Search
							case 5: 										
								boolean infFindAccount = true;
								boolean find;
								do {
									System.out.print("	Please Enter ID of Account: ");
									search = input.nextInt();
									find = list.InList(search);
									if(find != true) {
										System.out.println("	Entered Account Does Not Exist");
										System.out.println();
									}else {
										System.out.println("	Entered Account Found");
										list.PrintInList(search);
										System.out.println();
									}
								infFindAccount = false;	
								}while(infFindAccount);
									pause();
									break;
									
							//Get Balance of Account
							case 6:										
								boolean infGetBalance = true;
								boolean searchacc = false;
								float ShowBal = 0;
								do {
									System.out.print("	Please Enter ID of Account to Display Balance: ");
									search = input.nextInt();
									searchacc = list.InList(search);
									if(!searchacc) {
										System.out.println("	Account Does Not Exist!");
										System.out.println();
									} else 
										ShowBal = list.GetBalance(search);
										System.out.println("	Balance of Account "+ search + " is "+ ShowBal);
										System.out.println();
										infGetBalance = false;
									}while(infGetBalance);
								pause();
								break;
								
							//Linear Search for Account
							case 7:										
								boolean infGetName = true;
								boolean searchname = false;
								do {
									System.out.print("	Please Enter ID of Account to Find Name: ");
									search = input.nextInt();
									searchname = list.InList(search);
									if(searchname) {
										System.out.println("	Name of Account Number "+ search + " is " + list.GetName(search));
										System.out.println();
									}else
										System.out.println("	Account Does Not Exist!");
										System.out.println();
										infGetName = false;
								}while (infGetName);
								pause();
								break;
							
							case 8:
								System.out.println("	Please Fill Up Account information. . .");
								System.out.print("	Enter First Name - Middle Initial. - Last Name: ");
								String addAdminName;
								input.nextLine();
								addAdminName = input.nextLine();
								System.out.print("	Admin ID: ");
								int addAdminAccnum = input.nextInt();
								System.out.print("	Address: ");
								String addAdminAddress;
								input.nextLine();
								addAdminAddress = input.nextLine();
								System.out.print("	Contact Number: ");
								String addAdminContactNum = input.nextLine();
								System.out.print("	PIN: ");
								int addAdminPin = input.nextInt();
								Admin newadmin = new Admin(addAdminName, addAdminAccnum, addAdminAddress, addAdminContactNum, addAdminPin);
								adlist.AddAdmin(newadmin);
								
								insertAdmin(addAdminAccnum,addAdminName,addAdminAddress,addAdminContactNum,addAdminPin);
								
								System.out.println();
								System.out.println("	Admin Created Successfully!");
								writeToFileAdmin(adlist);
								pause();
								break;

							case 9:
								boolean infDeleteAdmin = true;
								boolean DelExistAdmin = false;
								Admin admin;
								if(adlist.isEmpty()) {
									System.out.println("	List Is Empty!");
									System.out.println();
								}else
								do {
									System.out.print("	Please Enter ID of Admin to Delete/Close: ");
									search = input.nextInt();
									DelExistAdmin = adlist.InList(search);
									if(!DelExistAdmin) {
										System.out.println("	Entered Account Does Not Exist");
										System.out.println();
									}else {
										admin = adlist.DeleteAdmin(search);
										System.out.println("	Account of "+ admin.Name + " has been closed" );
										System.out.println();
									}
									infDeleteAccount = false;
								}while(infDeleteAccount);
								writeToFileAdmin(adlist);
								pause();
								break;

							case 10:
								if(adlist.isEmpty()) {
									System.out.println();
									System.out.println("	There are Currently No Admins in the System");
									pause();
									break;
								}
								else {	
								//Menu to display choices the desired action for sorting/
								//displaying list of admins currently in the system							
									adlist.PrintAll();
									pause();
									break;	
								}

							//Go Back
							case 11:										
								loop2 = true;
								break;
								
							default:
								System.out.println("	Enter a valid choice.");	
								pause();
						}
					}while (loop2 != true);
					break;
					
				default:
					System.out.println("	Enter a valid choice.");
					pause();
			}
			
		} while (loop !=true);
		System.out.println();
		System.out.println("	Thank you for using the BANKING SYSTEM");
		System.out.println();
	}

	public static void pause(){
		Scanner pause = new Scanner(System.in);
		System.out.println();
		System.out.print("	Press enter to continue . . .");
		String c = pause.nextLine();
		clearScreen();
	}

	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();
	}
	
	public static Connection getConnection() throws Exception {
		try
		{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/cc14?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "root";
			String password = "";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.print("Database is connected !");
			return conn;
		}
			catch(Exception e)
		{
			System.out.print("Do not connect to DB - Error:"+e);
		}
		return null;
	}
	
	public static void insertCustomer(String Name,String Address,String Contact_Number, int CustomerID, int AccountID) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO customer(Name,Address,Contact_Number,CustomerID,AccountID,TransactionID) VALUES(" + Name + "','" + Address + "','" + Contact_Number + "'," + CustomerID + "," + AccountID + ")");
			ps.execute();
			ps.close();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Customer added...");
		}
	}
	
	public static void insertAdmin(int id, String name, String address, String contactNum ,int pin) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO administrator (AdminID,Name,Address,Contact_Number,AdminPin) VALUES(" + id + ",'" + name + "','" + address + "','" +  contactNum + "'," + pin + ")");
			ps.execute();
			ps.close();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Admin added...");
		}
	}
	
	public static void insertAccount(int AccountID, int PIN, float Balance,int CustomerID, int AdminID) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO savings_account(AccountID,PIN,Balance,CustomerID,AdminID) VALUES(" + AccountID + "," + PIN + "," + Balance + "," + CustomerID + "," + AdminID + ")");
			ps.execute();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Account added...");
		}
	}
	
	public static void insertTransaction(String TransactionID, String action){
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO transaction(Action,TransactionID) VALUES('"+ action + "','" + TransactionID + "')");
			ps.execute();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Transaction added...");
		}
	}
	
	public static void insertAccountBalance(int AccountID, float Balance){
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO account_balance(AccountID,Balance) VALUES(" + AccountID + "," + Balance + ")");
			ps.execute();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("AccountBalance added...");
		}
	}
	
	public static void alterBalance(int AccountID, float Balance) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE account_balance SET Balance = " + Balance + "WHERE (AccountID =" + AccountID + ")");
			ps.execute();
		}catch(Exception e) {System.out.println(e);}
		finally {
			System.out.println("Balance updated...");
		}
	}
	//UPDATE `cc14`.`account_balance` SET `Balance` = '11000' WHERE (`AccountID` = '2020');
}