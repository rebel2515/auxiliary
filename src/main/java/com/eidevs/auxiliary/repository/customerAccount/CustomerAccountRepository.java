/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.repository.customerAccount;

import com.eidevs.auxiliary.model.customerAccount.T24Accounts;
import com.eidevs.auxiliary.model.customerAccount.T24Customers;

/**
 *
 * @author eisrael
 */
public interface CustomerAccountRepository {

    T24Customers getCustomerDetails(String mobileNumber);

    T24Accounts createT24Accounts(T24Accounts t24Account);
    
    T24Customers createCustomer(T24Customers customer);

    T24Accounts getT24AccountUsingPhoneNumber(String mobileNumber);
    
    T24Customers getCustomerRecord(String customerId);
}
