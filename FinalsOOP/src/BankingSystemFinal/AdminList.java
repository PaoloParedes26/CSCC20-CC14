package BankingSystemFinal;

/**
 * @author Manduro I., Paredes J.
 * This class contains a linked list about the admins
 */

//Bank Admin Linked List and Available Methods
import java.io.Serializable;
import java.text.*;
public class AdminList implements Serializable {

	protected AdminListNode head;
	protected AdminListNode tail;

	int ListSize = 0;

	/**
	  * Constructor
	  */
	public AdminList() {
		head = tail = null;
	}

	/**
	  * method GetListSize - this gets the number of admins created
	  * @return ListSize - the number of admins in the list
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
	 * method AddAccount - enables the user to add an admin
	 * @param add - the admin to be added
	 */
	public void AddAdmin(Admin add) {
		if (!isEmpty()){
			tail.next = new AdminListNode(add);
			tail = tail.next;
		} else head = tail = new AdminListNode(add);
		ListSize++;
	}
	
	/**
	 * method DeleteAdmin - enables the user to remove an admin
	 * @param accnum - admin ID input by the user
	 * @return del - returns the deleted admin
	 */
	public Admin DeleteAdmin(int accnum) {
		Admin del = null;
		AdminListNode temp;
		for(temp = head; temp.info.AdminID != accnum; temp = temp.next);
			if(temp.info.AdminID == accnum) {
				del = temp.info;
				if(head == tail && del.AdminID == head.info.AdminID){
					head=tail=null;
				}
				else if(del.AdminID == head.info.AdminID) {
					head = head.next;	
				}
				else {
					AdminListNode pred, temp2;
					for(pred = head, temp2 = head.next; temp2 != null && temp2.info.AdminID != del.AdminID; pred = pred.next, temp2 = temp2.next);
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
	  * method PinList - helps the user find an admin pin in the list
	  * @param pin - pin number input by the user
	  * @return temp2 - if the pin number has a match in the list
	  */
	public boolean PinList(int pin) {
		AdminListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AdminPin != pin; temp2 = temp2.next);
		return temp2 != null;
	}
	
	/**
	  * method PinList - helps the user find an admin pin in the list
	  * @param accnum - admin id input by the user
	  * @return temp2 - if the pin number has a match in the list
	  */
	public boolean InList(int accnum) {
		AdminListNode temp2;
		for (temp2 = head; temp2 != null && temp2.info.AdminID != accnum; temp2 = temp2.next);
		return temp2 != null;
	}

	/**
	  * method PrintAll - this method prints all accounts according to chronological order of creation
	  */
	public void PrintAll() {
		System.out.println();
		System.out.printf("	%-17s %-17s %-17s %-17s %-17s\n", "NAME", "ADMIN ID", "ADDRESS", "CONTACT NUMBER", "PIN");
		for (AdminListNode temp = head; temp != null; temp = temp.next) {
			System.out.println();
			System.out.printf("	%-17s %-17s %-17s %-17s %-17s\n", temp.info.Name, temp.info.AdminID , temp.info.AdminAddress, temp.info.AdminContactNumber, temp.info.AdminPin);
			System.out.println();
		}	
	}
}
