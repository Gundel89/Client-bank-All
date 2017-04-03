/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.bank.services;

import com.mycompany.client.bank.jpa.Transaction;
import com.mycompany.client.bank.repository.AccountRepository;
import com.mycompany.client.bank.repository.TransactionRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author artem
 */
@Service
public class TransactionService {
	@Autowired
	TransactionRepository transactionRep;
        @Autowired
        AccountRepository accountRep;
	public List<Transaction> getAll() {
		return transactionRep.findAll();
	}
	
	public List<Transaction> getByAccountId(Long id) {
		//достань транзакции для определенного аккаунта по его айдишки
		List<Transaction> tr = transactionRep.findByAccountId(accountRep.findOne(id));
		tr.sort((Transaction t1, Transaction t2) -> {
			int val = 0;
			if(t1.getDate().before(t2.getDate())) {
				val = -1;
			} else if(t1.getDate().after(t2.getDate())) {
				val = 1;
			}
			return val;
		});
                return tr;
	}

	public Transaction findByTransactionId(Long id) {
		return transactionRep.findOne(id);
	}

	public Transaction addTransaction(Transaction transaction) {
		return transactionRep.save(transaction);
	}

	public void delTransaction(Long id) {
		Transaction t = transactionRep.findOne(id);
		if (t != null)
			transactionRep.delete(t);
	}

	public Transaction updateTransaction(Transaction transaction) {
		return transactionRep.save(transaction);
	}

	public List<Transaction> findByValue(int value1, int value2) {
		return transactionRep.findByValueBetween(value1, value2);
	}

	public List<Transaction> findByDate(Date date1, Date date2) {
		return transactionRep.findByDateBetween(date1, date2);
	}
}
