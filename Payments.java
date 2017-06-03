package Loan;

import java.util.Date;

public class Payments extends Loan {
	
	protected Date paymentDate;
	private double paymentAmount;

	public Payments(Date paymentDate, double paymentAmount)
	{
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
	}
	
	public double getPaymentAmount()
	{
		return this.paymentAmount;
	}
}
