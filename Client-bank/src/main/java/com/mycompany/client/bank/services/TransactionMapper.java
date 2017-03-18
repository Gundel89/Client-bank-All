/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.bank.services;

import com.mycompany.client.bank.api.LibTransaction;
import com.mycompany.client.bank.jpa.Account;
import com.mycompany.client.bank.jpa.Transaction;
import com.mycompany.client.bank.repository.AccountRepository;
import com.mycompany.client.bank.repository.TransactionRepository;
import com.mycompany.client.bank.utils.String_Date_util;
import java.time.Instant;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author artem
 */
@Component
public class TransactionMapper {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
     public LibTransaction fromInternal(Transaction t) {
        LibTransaction lt = null;
        if (t != null) {
            lt=new LibTransaction();
            lt.transactionId=t.getTransactionId();
            lt.date=t.getDate().toString();
            lt.value=String.valueOf(t.getValue());
            lt.accountId=t.getAccountId().getAccountId();
        }
        return lt;
    }
        public Transaction toInternal(LibTransaction lt) {
        Transaction t = null;
        //first, check if it exists
        if (lt.transactionId != null) {
           t = transactionRepository.findOne(lt.transactionId);
        }
        Account temp_acc=accountRepository.findOne(lt.accountId);
        if(temp_acc==null){
            //debug here
            return null;
        }
        if (t == null) { //not found, create new
            //logger.debug("Creating new user");
            t = new Transaction(lt.transactionId, Integer.valueOf(lt.value), String_Date_util.String_to_Date(lt.date));
        }
        else{
        //logger.debug("Updating existing user");
        t.setTransactionId(lt.transactionId);
        t.setValue(Integer.valueOf(lt.value));
        t.setDate(String_Date_util.String_to_Date(lt.date));
        }
        t.setAccountId(temp_acc);
        return t;
    }
}