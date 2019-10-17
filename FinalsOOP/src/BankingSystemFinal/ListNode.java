package BankingSystemFinal;

/**
 * @author Manduro I., Paredes J.,
 * Section: A
 * This class contains the nodes for the account
 */

//Node for Account Linked List
import java.io.Serializable;
public class ListNode implements Serializable{
	public Account info;
	public ListNode next;
	
	public ListNode(Account i) {
		this(i,null);
	}
	 public ListNode(Account i, ListNode n){
		info = i; next = n;
	}	 
}
