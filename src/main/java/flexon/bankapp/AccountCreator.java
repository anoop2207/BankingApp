package flexon.bankapp;

import java.util.Scanner;

import org.hibernate.Session;


public class AccountCreator {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
		
		
		BankAccount account = new BankAccount();
		account.setCustomerName("Anoop");
		account.setPhoneNumber("5123211223");
		account.setEmail("av@gmail.com");
		AccountCreator.display(account);
		boolean flag =true;
		System.out.println("Do you want to continue? (y/n)");
		while(flag) {
			Scanner scanner2 = new Scanner(System.in);
			String str = scanner2.nextLine();
			if(str.toLowerCase().equals("y") || str.toLowerCase().equals("n")) {
				if(str.toLowerCase().equals("n")) {
					flag=false;
					System.out.println("Thank you.");
				}
				if(str.toLowerCase().equals("y")) {
					AccountCreator.display(account);
					System.out.println("Do you want to continue? (y/n)");
				}
			}else {
				System.out.println("Please enter a valid input (y/n)");
				
			}
			
		}		
	}

	public static void display(BankAccount account) {
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
				account.deposit(amount);
				break;
			case 2:
				System.out.println("Enter Withdraw Amount");
				double amount1 = sc.nextDouble();
				account.withdraw(amount1);
				break;
			case 3:
				System.out.println(account.accountDetails());
				break;
			default:
				System.out.println("Proper input is not selected");	
		}
		System.out.println("------------------------------------------");
	}
}
