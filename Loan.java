package Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Loan {
	
	private double startingBalance;
	private double currentBalance;
	private double interestRate;
	private String interestFrequency;
	private Date loanStartDate;	
	private Date loanEndDate;
	private ArrayList<Payments> payments;
	
	public Loan()
	{
		this.startingBalance = 0;
		this.currentBalance = 0;
		this.interestRate = 0;
		this.interestFrequency = null;
		this.loanStartDate = null;
		this.loanEndDate = null;
		this.payments = new ArrayList<Payments>();
	}
	public Loan(double startingBalance)
	{
		this();
		this.startingBalance = startingBalance;
		this.currentBalance = startingBalance;
	}
	
	public Loan(double startingBalance, double interestRate)
	{
		this(startingBalance);
		this.interestRate = interestRate;
	}
	
	public double getStartingBalance()
	{
		return this.startingBalance;
	}
	
	public void setStartDate(String startDate) throws ParseException
	{
		this.loanStartDate = new SimpleDateFormat("dd MM yyyy").parse(startDate);
	}
	
	public Date getStartDate()
	{
		return this.loanStartDate;
	}
	
	public long getLoanStartDate()
	{
		return this.loanStartDate.getTime();
	}
	
	private void updateCurrentBalance()
	{		
			
			for(Payments pay: payments)
			{
				if (payments.indexOf(pay) == 0)
				{
					int daysBetweenPayments = (int)TimeUnit.MILLISECONDS.toDays(pay.paymentDate.getTime()) - (int)TimeUnit.MILLISECONDS.toDays(loanStartDate.getTime());
					System.out.println("This many days until 1st payment " + daysBetweenPayments);
					for(short g = 1; g <= daysBetweenPayments; g++)				
					{
						 this.currentBalance += this.currentBalance * this.interestRate;					
					}	
				}
				else
				{
					int daysBetweenPayments = (int)TimeUnit.MILLISECONDS.toDays(pay.paymentDate.getTime()) - (int)TimeUnit.MILLISECONDS.toDays(payments.get(payments.indexOf(pay) - 1).paymentDate.getTime());
					System.out.println("This many days until 1st payment " + daysBetweenPayments);
					for(short g = 1; g <= daysBetweenPayments; g++)				
					{
						 this.currentBalance += this.currentBalance * this.interestRate;					
					}	
				}			
				this.currentBalance -= pay.getPaymentAmount();				
			}
			int daysSinceStartOfLoan = (int)TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()) - (int)TimeUnit.MILLISECONDS.toDays(payments.get(payments.size() - 1).paymentDate.getTime());
			System.out.println("This many days after 1st payment " + daysSinceStartOfLoan);
			for(short i = 1; i < daysSinceStartOfLoan; i++)
			{
				 this.currentBalance += this.currentBalance * this.interestRate;
			}	
	}
	
	public double getCurrentBalance()	
	{
		updateCurrentBalance();
		return this.currentBalance;
	}

	public double getInterestRate()
	{
		return this.interestRate;
	}	

	public void makePayment(Payments newPayment)
	{
		payments.add(newPayment);		
	}
}
