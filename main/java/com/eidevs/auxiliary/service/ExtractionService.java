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
public interface ExtractionService {
    String updateUserStatus();
    
    String createBillerPackage();
    
    String sortFT();
    
    //String updateStmtRecordWithClosingBalance();
    
    String updateStmtRecordWithOpeningBalance();
    
    String updatePHCNBillerCode();
}
