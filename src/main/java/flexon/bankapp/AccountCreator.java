package flexon.bankapp;

import java.util.Scanner;

import org.hibernate.Session;


public class AccountCreator {
	private static BankAccountService accountService = new BankAccountService();
	public static void main(String[] args) {
		boolean flag =true;
		displayMainMenu();
		System.out.println("Do you want to continue? (y/n)");
		while(flag) {
			Scanner scanner = new Scanner(System.in);
			String str = scanner.nextLine();
			if(str.toLowerCase().equals("y") || str.toLowerCase().equals("n")) {
				if(str.toLowerCase().equals("n")) {
					flag=false;
					System.out.println("Thank you.");
				}
				if(str.toLowerCase().equals("y")) {
					displayMainMenu();
					System.out.println("Do you want to continue? (y/n)");
				}
			}else {
				System.out.println("Please enter a valid input (y/n)");	
			}
		}		
	}
	
	private static void displayMainMenu() {
		System.out.println("------------------------------------------");
		System.out.println("Options");
		System.out.println("1. Create Account \t 2. Retrieve Account \n");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option) {
			case 1:
				createAccount();
				break;
			case 2:
				retrieveAccount();
				break;
			default:
				System.out.println("Invalid input. Please run the program again.");
		}
	}

	private static void retrieveAccount() {
		System.out.println("Please enter your account number");
		Scanner sc = new Scanner(System.in);
		Long accountNumber = sc.nextLong();
		AccountEntity entity = accountService.fetchAccountDetails(accountNumber);
		if(entity!=null) {
			display(entity);
		}else {
			System.out.println("No account found with the provided account number.");
		}
	}

	private static void createAccount() {
		AccountEntity accountEntity = new AccountEntity();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your name");
		String name = sc.next();
		accountEntity.setCustomerName(name);
		System.out.println("Please enter your phone number in the format xxxxxxxxxx");
		String phoneNumber = sc.next();
		int count =0;
		while(count++<=5) {
			if(accountService.validatePhoneNumber(phoneNumber)) {
				accountEntity.setPhoneNumber(phoneNumber);
				break;
			}else {
				System.out.println("Invalid Format! Please enter your phone number in the format xxxxxxxxxx");
				if(count==5) System.out.println("This is your last attempt.");
				phoneNumber = sc.next();
			}
		}
		System.out.println("Please enter your email id");
		String email = sc.next();
		accountEntity.setEmail(email);
		Long accountNumber  = accountService.createBankAccount(accountEntity);
		System.out.println("Succesfully created your account. This is you account number: "+accountNumber);
	}
	
	private static void display(AccountEntity  account) {
		System.out.println("------------------------------------------");
		System.out.println("Options");
		System.out.println("1. Deposit \t 2. Withdraw\n");
		System.out.println("3. Account Details");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch (option) {
			case 1:
				System.out.println("Enter Deposit Amount");
				double amount = sc.nextDouble();
				Double balance = accountService.deposit(account,amount);
				System.out.println(amount+" deposited. New Balance is "+balance);
				break;
			case 2:
				System.out.println("Enter Withdraw Amount");
				double amount1 = sc.nextDouble();
				Double balance1 =accountService.withdraw(account,amount1);
				if(balance1!=null) {
					System.out.println(amount1+" withdrawn. New Balance is "+balance1);
				}else {
					System.out.println("Insufficient Funds.");
				}
				break;
			case 3:
				System.out.println(accountService.accountDetails(account));
				break;
			default:
				System.out.println("Proper input is not selected");	
		}
		System.out.println("------------------------------------------");
	}
}
