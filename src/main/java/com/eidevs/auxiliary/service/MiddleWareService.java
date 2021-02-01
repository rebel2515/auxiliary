/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.service;

/**
 *
 * @author eisrael
 */
public interface MiddleWareService {
    Boolean checkRequestHeaderValidity(String authorization);
    
    String Withdrawal(String requestBody);
    
    String onlineAccountOpening(String requestBody);
    
    String updateTellerTemp(String requestBody);
    
    String updateTellerHistory();
    
    String writeCustomerDetail(String requestBody);
}
