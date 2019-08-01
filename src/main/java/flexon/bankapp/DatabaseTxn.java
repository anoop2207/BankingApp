package flexon.bankapp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class DatabaseTxn {
	static Session session = HibernateUtil.getSessionFactory().openSession();
	public static long getAccountNumber() {
		session.beginTransaction();
		String hql = "SELECT E.customer_name FROM Account E";
		Query query = session.createQuery(hql);
		List results = query.list();
		System.out.println(results);
		return 0;
	}
	public static void main(String[] args) {
		getAccountNumber();
	}
}
