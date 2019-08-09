package flexon.bankapp;

import java.util.Random;

public class BankAccountService {
	private BankAccountDAO accountDAO  = new  BankAccountDAO();
	
	public long createBankAccount(AccountEntity accountEntity) {
		Long accNumber = 0l;
		AccountEntity entity1 = accountDAO.getLatestEntry();
		if(entity1!=null) {
			accNumber = entity1.getAccountNumber()+1; 
		}else {
			Random rand = new Random();
			accNumber = (long) (700000000+ rand.nextInt(90000));
		}
		accountEntity.setAccountNumber(accNumber);
		accountDAO.createAccount(accountEntity);
		return accountEntity.getAccountNumber();
	}

	public Boolean validatePhoneNumber(String phoneNumber) {
		if(phoneNumber.length()!=10) {
			return false;
		}else {
			if(phoneNumber.matches("^\\d{10}")) {
				return true;
			}else {
				return false;
			}
		}
	}

	public AccountEntity fetchAccountDetails(long accountNumber) {
		return accountDAO.retrieveBankAccount(accountNumber);
	}
		
	public Double deposit(AccountEntity entity, Double amount) {
		return accountDAO.depositAmount(entity,amount);
	}

	public Double withdraw(AccountEntity entity, Double amount) { 
		if(amount>entity.getBalance()) {
			return null;
		}else {
			return accountDAO.withdrawAmount(entity,amount);
		}
	}
	 
	public String accountDetails(AccountEntity entity) { 
		String retString ="Account Details\n------------------------------------------"; 
		retString +="\nCustomer Name  : \t"+entity.getCustomerName(); 
		retString +="\nAccount Number : \t"+entity.getAccountNumber(); 
		retString +="\nBalance        : \t"+entity.getBalance(); 
		retString +="\nEmail          : \t"+entity.getEmail(); 
		retString +="\nPhone Number   : \t"+entity.getPhoneNumber(); 
		retString +="\n------------------------------------------"; 
		return retString; 
	}
}
