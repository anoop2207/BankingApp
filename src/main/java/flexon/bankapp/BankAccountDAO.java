package flexon.bankapp;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankAccountDAO {
	private static Session session; 
	private static Transaction transaction =null;

	
	public AccountEntity retrieveBankAccount(long accountNumber) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AccountEntity ae WHERE ae.accountNumber="+accountNumber;
		Query query = session.createQuery(hql);
		List<AccountEntity> ae = query.list();
		transaction.commit();
		session.close();
		if(ae.size()>0) return ae.get(0);
		return null;
	}
	
	public int createAccount(AccountEntity entity) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		Integer id = (Integer)session.save(entity);
		transaction.commit();
		session.close();
		entity.setAccountId(id);
		return id;
	} 

	public AccountEntity getLatestEntry() {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		String hql = "FROM AccountEntity ae ORDER BY ae.accountNumber DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		List<AccountEntity> result  = query.list();
		transaction.commit();
		session.close();
		if(result.size()>0) return result.get(0);
		return null;
	}

	public Double depositAmount(AccountEntity account, Double amount) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		AccountEntity entity = (AccountEntity) session.get(AccountEntity.class, account.getAccountId());
		entity.setBalance(entity.getBalance()+amount);
		session.update(entity);
		transaction.commit();
		session.close();
		return entity.getBalance();
	}
	
	public Double withdrawAmount(AccountEntity account, Double amount) {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		AccountEntity entity = (AccountEntity) session.get(AccountEntity.class, account.getAccountId());
		entity.setBalance(entity.getBalance()-amount);
		session.update(entity);
		transaction.commit();
		session.close();
		return entity.getBalance();
	}
}
