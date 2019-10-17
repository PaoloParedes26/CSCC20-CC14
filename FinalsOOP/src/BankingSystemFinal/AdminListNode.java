package BankingSystemFinal;
/**
  * @author Manduro I., Paredes J.
  * Section: A
  * This class contains the nodes for the admin
  */

//Node for Admin Linked List
import java.io.Serializable;
public class AdminListNode implements Serializable {
	public Admin info;
	public AdminListNode next;

	public AdminListNode(Admin i) {
		this(i, null);
	}
	public AdminListNode(Admin i, AdminListNode n) {
		info = i; next = n;
	}
}
