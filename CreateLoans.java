package Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class CreateLoans {

	public static void main(String[] args) throws ParseException {
		 Loan loanee = new Loan(1995, (.065 / 365));
		 loanee.setStartDate("10 05 2017");		
		 loanee.makePayment(new Payments(new SimpleDateFormat("dd MM yyyy").parse("26 05 2017"), 200.00));	
		 System.out.printf("$%.2f", loanee.getCurrentBalance());
		 System.out.println();
		 System.out.printf("Your minimum payment is: $%.2f", loanee.getMinimumPayment());
		 System.out.println();
		 System.out.println();
		 
		 Loan loanee2 = new Loan(3000, (.075 / 365));
		 loanee2.setStartDate("19 06 2017");	
		 System.out.printf("$%.2f", loanee2.getCurrentBalance());
		 System.out.println();
		 System.out.printf("Your minimum payment is: $%.2f", loanee2.getMinimumPayment());
		 }
	
		
	
		

}
