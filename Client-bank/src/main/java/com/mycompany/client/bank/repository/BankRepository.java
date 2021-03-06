/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client.bank.repository;
import com.mycompany.client.bank.jpa.Bank;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author artem
 */
public interface BankRepository extends CrudRepository<Bank, Long>{
     @Override
    public List<Bank> findAll();
    public Bank findByNameIgnoreCase(String Name);
    public List<Bank> findByNameContainingIgnoreCase(String Name);
    public List<Bank> findByDepositPersentGreaterThan(int depositPersent);
    public List<Bank> findByCreditPersentLessThan(int creditPersent);
    public List<Bank> removeByNameIgnoreCase(String Name);
    public List<Bank> removeByDepositPersentBetween(int deposit_persent1, int deposit_persent2);
    public List<Bank> removeByCreditPersentBetween(int credit_persent1, int credit_persent2);
}
