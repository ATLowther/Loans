package Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateLoans {

	public static void main(String[] args) throws ParseException {
		 Loan loaner = new Loan(1995, (.065 / 365));
		 loaner.setStartDate("10 05 2017");		
		 loaner.makePayment(new Payments(new SimpleDateFormat("dd MM yyyy").parse("26 05 2017"), 200.00));	
		 System.out.printf("$%.2f", loaner.getCurrentBalance());
	}

}
