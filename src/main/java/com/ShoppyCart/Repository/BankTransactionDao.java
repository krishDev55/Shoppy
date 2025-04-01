package com.ShoppyCart.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ShoppyCart.entity.BankAccount;
import com.ShoppyCart.entity.BankTransaction;

@Repository

public class BankTransactionDao {
	public Session session;

	public BankTransactionDao (SessionFactory sessionFactory) {
		session=sessionFactory.openSession();
	}

	public void saveBankTnx(BankTransaction bankTnx) {
		
		Transaction tnx = session.beginTransaction();
		session.persist(bankTnx);
			tnx.commit();
			
	}

	public void updateBankTnx(BankTransaction bankTnx) {
		session.persist(bankTnx);
	}

	public BankTransaction getBankTnxById(int tnxId) {
		return session.find(BankTransaction.class, tnxId);
	}

	public List<BankTransaction> getAllBankTnx() {
		return null;
	}

}
