package BankingSystemFinal;

/**
 * @author Manduro I., Paredes J.
 * Section: A
 * Description: This class contains the main method
 */

import java.util.*;
import java.io.*;
import java.text.*;

public class Main {

	public static void main(String[] args) throws Exception{
		
		Run run = new Run();
		run.getConnection();
		run.runProgram();
	}

}
