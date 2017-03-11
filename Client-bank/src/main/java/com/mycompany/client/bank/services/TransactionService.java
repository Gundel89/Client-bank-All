/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.bank.services;

import com.mycompany.client.bank.jpa.Transaction;
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
    TransactionRepository transactionrep;
    public List<Transaction> getAll(){
    return transactionrep.findAll();
    }
    public Transaction findByTransactionId(Long id){
    return transactionrep.findOne(id);
    }
    public Transaction addTransaction(Transaction transaction) {
        return transactionrep.save(transaction);
    }
public void delTransaction(Long id){
        Transaction t = transactionrep.findOne(id);
        if(t!=null)transactionrep.delete(t);
    }
public Transaction updateTransaction(Transaction  transaction) {
        return transactionrep.save(transaction);
    }
public List<Transaction> findByValue(int value1,int value2){
return transactionrep.findByValueBetween(value1, value2);
}
public List<Transaction> findByDate(Date date1, Date date2){
return transactionrep.findByDateBetween(date1, date2);
}
}
