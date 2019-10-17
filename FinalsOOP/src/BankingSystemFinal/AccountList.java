package BankingSystemFinal;

/**
 * @author Manduro I., Paredes J.
 * This class contains a linked list about the accounts
 */

//Bank Account Linked List and Available Methods
import java.io.Serializable;
import java.text.*;
public class AccountList implements Serializable{

	protected ListNode head;
	protected ListNode tail;
			  
	int ListSize = 0;
	DecimalFormat Billion = new DecimalFormat("##0.####");

	/*
	 * Constructor
	 */
	public AccountList() {
		head = tail = null;
	}

	/**
	 * method GetListSize - this gets the number of accounts created
	 * @return ListSize - the number of accounts in the list
	 */
	public int getListSize() {
		return ListSize;
	}

	/**
	 * method isEmpty -  This method checks if the list is Empty
	 * @return true if list isEmpty
	 * @return false if list is not empty
	 */
	public boolean isEmpty() {
		if(head==null) {
			return true;
		} else
			return false;
	}
	
	/**
	 * method Deposit - this method enables the user to add money to his/her account
	 * @param accnum - the account number input by the user
	 * @param value - the desired number of money to deposit by the user
	 * @return value - the amount deposited by the user
	 */
	public float Deposit (int accnum, float value) {
		Account fromACC = null;
		ListNode temp;
		for(temp = head; temp.next != null && temp.info.AccNum != accnum; temp = temp.next);
			if(temp.info.AccNum == accnum) {
				fromACC = temp.info;
			}
			fromACC.Balance = fromACC.Balance + value;
			return value;
	}

	/**
	 * method Withdraw - this allows the user to get money from his/her account
	 * @param accnum - account number input by the user
	 * @param value - the desired amount to be withdrawn by the user
	 * @return value - the amount withdrawn  
	 */
	public float Withdraw (int accnum, float value) {
		Account fromACC = null;
		ListNode temp;
		for(temp = head; temp.next != null && temp.info.AccNum != accnum; temp = temp.next);
			if(temp.info.AccNum == accnum) {
				fromACC = temp.info;
			}
			fromACC.Balance = fromACC.Balance - value;
			return value;
	}
	/**
	 * method GetBalance - the method allows the user to input his/her account number then outputs the account's total balance
	 * @param accnum - account number input by the user
	 * @return temp2.info.Balance - the total balance of the user 
	 */
	public float GetBalance(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.Balance;
	}

  /**
   * method GetName - Gets the Name of the entered account number
   * @param accnum - account number input by the user
   * @return temp2.info.Name - name of the account number 
   */
	public String GetName(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.Pin != accnum; temp2 = temp2.next);
		return temp2.info.Name;
	}
	
	/**
	  * method GetAddress- Gets the Address of the entered account number
	  * @param accnum - account number input by the user
	  * @return temp2.info.Address - address of the account number
	  */
	public String GetAddress(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.Address;
	}

	/**
	  * method GetContactNum - Gets the Contact Number of the entered account number
	  * @param accnum - account number input by the user
	  *	@return temp2.info.ContactNumber - contact number of the account number
	  */
	public String GetContactNum(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.ContactNumber;
	}

	/**
	  * method GetCustomerID - Gets the Customer ID of the entered account number
	  * @param accnum - account number input by the user
	  * @return temp2.info.CustomerID
	  */
	public int GetCustomerID(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.CustomerID;
	}

	/**
	  * method GetPin - Gets the Pin of the entered account number
	  * @param accnum - account number input by the user
	  * @return temp2.info.Pin
	  */
	public int GetPin(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2.info.Pin;
	}

	/**
	 * method AddAccount - enables the user to add an account
	 * @param add - the account to be added
	 */
	public void AddAccount(Account add) {
		if (!isEmpty()){
			tail.next = new ListNode(add);
			tail = tail.next;
		} else head = tail = new ListNode(add);
		ListSize++;
	}
	
	/**
	 * method DeleteAccount - enables the user to remove a created account
	 * @param accnum - account number input by the user
	 * @return del - returns the deleted account
	 */
	public Account DeleteAccount(int accnum) {
		Account del = null;
		ListNode temp;
		for(temp = head; temp.info.AccNum != accnum; temp = temp.next);
			if(temp.info.AccNum == accnum) {
				del = temp.info;
				if(head == tail && del.AccNum == head.info.AccNum){
					head=tail=null;
				}
				else if(del.AccNum == head.info.AccNum) {
					head = head.next;	
				}
				else {
					ListNode pred, temp2;
					for(pred = head, temp2 = head.next; temp2 != null && temp2.info.AccNum != del.AccNum; pred = pred.next, temp2 = temp2.next);
					if(temp2 != null) {
						pred.next = temp2.next;
						if (temp2 == tail)
						tail = pred;
					}
				}	
			}
		ListSize--;
		return del;
	}

	/**
	 * method InList - helps the user find an account id in the list
	 * @param accnum - account number input by the user
	 * @return temp2 - if the input account number by the user has a match in the list, temp2 returns true otherwise, false.
	 */
	public boolean InList(int accnum) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
		return temp2 != null;
	}

	/**
	  * method PinList - helps the user find an account pin in the list
	  * @param pin - pin number input by the user
	  * @return temp2 - if the pin number has a match in the list
	  */
	public boolean PinList(int pin) {
		ListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.Pin != pin; temp2 = temp2.next);
		return temp2 != null;
	}

	/**
	 * method account PrintInLst - this method inputs a number and outputs the information of the object Account
	 * @param accnum - is the account number sent by the user
	 * @return temp2.info - prints the information of the specified account number
	 */
	public Account PrintInList(int accnum) {
			ListNode temp2;
			for (temp2 = head; temp2 != null && temp2.info.AccNum != accnum; temp2 = temp2.next);
			System.out.printf("	%-17s %-17s %-17s %-17s %-17s %-17s %-17s\n", temp2.info.Name  , temp2.info.AccNum , Billion.format(temp2.info.Balance), temp2.info.Address, temp2.info.ContactNumber, temp2.info.CustomerID, temp2.info.Pin);
			return temp2.info;
		}
	
	/**
	 * method Transfer - this method enables the user to transfer transactions in between two accounts
	 * @param from - account number of the source account
	 * @param to - account number of the destination account 
	 * @param amount - amount of money to transfer
	 */
	public void Transfer(int from, int to, float amount) {
		Account fromACC = null;
		Account toACC = null;
		//points to source account
		ListNode temp;
		for(temp = head; temp.next != null && temp.info.AccNum != from; temp = temp.next);
			if(temp.info.AccNum == from) {
				fromACC = temp.info;
			}
		//points to destination account
		ListNode temp2;
		for(temp2 = head; temp2.next != null && temp2.info.AccNum != to; temp2 = temp2.next);
			if(temp2.info.AccNum == to) {
				toACC = temp2.info;
			}
			fromACC.Balance = fromACC.Balance - amount;
			toACC.Balance = toACC.Balance + amount;
	}
	
	/**
	 *method PrintAll - this method prints all accounts according to chronological order of creation
	 */
	public void PrintAll() {
		System.out.println();
		System.out.printf("	%-17s %-17s %-17s %-17s %-17s %-17s %-17s\n", "NAME", "ACCOUNT ID", "BALANCE", "ADDRESS", "CONTACT NUMBER", "CUSTOMER ID", "PIN");
		for (ListNode temp = head; temp != null; temp = temp.next) {
			System.out.println();
			System.out.printf("	%-17s %-17s %-17s %-17s %-17s %-17s %-17s\n", temp.info.Name, temp.info.AccNum , Billion.format(temp.info.Balance), temp.info.Address, temp.info.ContactNumber, temp.info.CustomerID, temp.info.Pin);
			System.out.println();
		}	
	}
}