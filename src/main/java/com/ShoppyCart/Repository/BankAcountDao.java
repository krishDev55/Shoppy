package com.ShoppyCart.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.ShoppyCart.entity.BankAccount;

@Repository
public class BankAcountDao {
		
	public 	Session session;
		public BankAcountDao(SessionFactory sessionFactory) {
			session=sessionFactory.openSession();
		}

	public void saveBankAccount(BankAccount bankAccount) {
		Transaction tnx = session.beginTransaction();
		session.persist(bankAccount);
		tnx.commit();
		
	}
	public void updateBankAccount(BankAccount bankAccount) {
		Transaction tnx = session.beginTransaction();
		session.merge(bankAccount);
		tnx.commit();
	}
	
	
	@Cacheable(value = "BankAccount", key = "#accountId")
	public BankAccount getBankAccountById(long accountId) {
		return session.find(BankAccount.class, accountId);
	}
	
	
	public List<BankAccount> getAllBankAccount() {
		return null;
	}
	
	
}
