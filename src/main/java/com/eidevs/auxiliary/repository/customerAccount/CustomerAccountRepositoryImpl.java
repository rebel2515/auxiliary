/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.repository.customerAccount;

import com.eidevs.auxiliary.model.customerAccount.T24Accounts;
import com.eidevs.auxiliary.model.customerAccount.T24Customers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eisrael
 */
@Repository
public class CustomerAccountRepositoryImpl implements CustomerAccountRepository {

    @PersistenceContext(unitName = "customerAccountPersistenceUnit")
    EntityManager em;

    @Override
    @Transactional(transactionManager = "customerAccountTransactionManager")
    public T24Accounts createT24Accounts(T24Accounts t24Account) {
        em.persist(t24Account);
        em.flush();
        return t24Account;
    }

    @Override
    @Transactional(transactionManager = "customerAccountTransactionManager")
    public T24Customers createCustomer(T24Customers customer) {
        em.persist(customer);
        em.flush();
        return customer;
    }

    @Override
    public T24Customers getCustomerDetails(String mobileNumber) {
        TypedQuery<T24Customers> query = em.createQuery("SELECT t FROM T24Customers t WHERE t.mobileNumber = :mobileNumber", T24Customers.class)
                .setParameter("mobileNumber", mobileNumber);
        List<T24Customers> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record.get(0);
    }

    @Override
    public T24Accounts getT24AccountUsingPhoneNumber(String mobileNumber) {
        TypedQuery<T24Accounts> query = em.createQuery("SELECT t FROM T24Accounts t WHERE t.mobileNumber = :mobileNumber", T24Accounts.class)
                .setParameter("mobileNumber", mobileNumber);
        List<T24Accounts> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record.get(0);
    }

    @Override
    public T24Customers getCustomerRecord(String customerId) {
        TypedQuery<T24Customers> query = em.createQuery("SELECT t FROM T24Customers t WHERE t.customerId = :customerId", T24Customers.class)
                .setParameter("customerId", customerId);
        List<T24Customers> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record.get(0);
    }
}
