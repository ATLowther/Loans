package Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Loan 
{
	
	private double startingBalance;
	public double currentBalance;
	private double interestRate;
	private String interestFrequency;
	private double minPaymentPercentage;
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
		this.minPaymentPercentage = 0.01;
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
					for(short g = 1; g <= daysBetweenPayments; g++)				
					{
						 this.currentBalance += this.currentBalance * this.interestRate;					
					}	
				}
				else
				{
					int daysBetweenPayments = (int)TimeUnit.MILLISECONDS.toDays(pay.paymentDate.getTime()) - (int)TimeUnit.MILLISECONDS.toDays(payments.get(payments.indexOf(pay) - 1).paymentDate.getTime());
					for(short g = 1; g <= daysBetweenPayments; g++)				
					{
						 this.currentBalance += this.currentBalance * this.interestRate;					
					}	
				}			
				this.currentBalance -= pay.getPaymentAmount();	
				int daysSinceStartOfLoan = (int)TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis()) - (int)TimeUnit.MILLISECONDS.toDays(payments.get(payments.size() - 1).paymentDate.getTime());
				for(short i = 1; i < daysSinceStartOfLoan; i++)
				{
					 this.currentBalance += this.currentBalance * this.interestRate;
				}	
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
	
	public double getMinimumPayment()
	{
		return ((this.currentBalance * this.minPaymentPercentage) > 20.0) ? this.currentBalance * this.minPaymentPercentage : 20.0;
	}
	
	
	
	public static void payOffBalance()
	{
		double x = 2000.00;
		payOffBalance(x);
	}
	
	public static void payOffBalance(double x)
	{
		double balance = x;
		int paymentNum = 0;
		double interestRate = .15;
		System.out.println("Current balance: " + balance);
		while (balance > 0.00)
		{
			balance += (balance * (interestRate/365));
			double minPayment = ((balance * .01) > 20) ? balance * .01 : 20;
			if (minPayment > balance)
				balance -= balance;
			else
				balance -= minPayment;
			paymentNum++;
			System.out.printf("Your new balance is: $%.2f, Payment num: %d", balance, paymentNum);
			System.out.println();		
		}
	}
	
	public static void payOffBalance(Loan loanee)
	{
		double balance = loanee.currentBalance;
		int paymentNum = 0;
		double interestRate = loanee.interestRate;
		System.out.printf("Current balance: $%.2f\n", balance);
		while (balance > 0.00)
		{
			balance += (balance * (interestRate/365));
			double minPayment = ((balance * .01) > 20) ? balance * .01 : 20;
			if (minPayment > balance)
				balance -= balance;
			else
				balance -= minPayment;
			paymentNum++;
			System.out.printf("Your new balance is: $%.2f, Payment num: %d", balance, paymentNum);
			System.out.println();		
		}
		
	}
	
	
}
