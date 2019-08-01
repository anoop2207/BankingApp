package flexon.bankapp;

import java.util.Random;

public class BankAccount {
	private long accountNumber;
	private double balance;
	private String customerName;
	private String email;
	private String phoneNumber;
	
	public BankAccount() {
		System.out.println("Opening a bank account.");
		Random rand = new Random();
		accountNumber = 700000000+rand.nextInt(90000);
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if(phoneNumber.length()!=10) {
			System.err.println("Invalid Number!. Please try again.");
			return;
		}else {
			if(phoneNumber.matches("^\\d{10}")) {
				this.phoneNumber = phoneNumber;
			}else {
				System.err.println("Invalid format. Please enter in this format '1234567890'");
			}
		}
	}
	
	public void deposit(Double amount) {
		this.balance += amount;
		System.out.println("Amount Deposited. New Balance is "+this.balance);
		
	}
	
	public void withdraw(Double amount) {
		if (amount>this.balance) {
			System.out.println("Insufficient Funds.");
			return;
		}
		this.balance -= amount;
		System.out.println(amount+" withdrawn. Remaining Funds are "+this.balance);
	}
	
	public String accountDetails() {
		String retString = "Account Details\n------------------------------------------";
		retString += "\nCustomer Name  : \t"+this.customerName;
		retString += "\nAccount Number : \t"+this.accountNumber;
		retString += "\nBalance        : \t"+this.balance;
		retString += "\nEmail          : \t"+this.email;
		retString += "\nPhone Number   : \t"+this.phoneNumber;
		retString += "\n------------------------------------------";
		return retString;
	}
}
