/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.model.customerAccount;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author eisrael
 */
@Entity
@Table(name = "accountDetails")
public class T24Accounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "bvn")
    private String bvn;
    @Column(name = "t24AccountNumber")
    private String t24AccountNumber;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "accountName")
    private String accountName;
    @Column(name = "mobileNumber")
    private String mobileNumber;
    @Column(name = "accountRestriction")
    private String accountRestriction;
    @Column(name = "accountStatus")
    private String accountStatus;
    @Column(name = "customerId")
    private String customerId;
    @Column(name = "currency")
    private String currency;
    @Column(name = "branchCode")
    private String branchCode;
    @Column(name = "branchName")
    private String branchName;
    @Column(name = "accountType")
    private String accountType;
    @Column(name = "productCode")
    private String productCode;
    @Column(name = "accountDescription")
    private String accountDescription;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "categoryCode")
    private String categoryCode;
    @Column(name = "openingDate")
    private String openingDate;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getT24AccountNumber() {
        return t24AccountNumber;
    }

    public void setT24AccountNumber(String t24AccountNumber) {
        this.t24AccountNumber = t24AccountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountRestriction() {
        return accountRestriction;
    }

    public void setAccountRestriction(String accountRestriction) {
        this.accountRestriction = accountRestriction;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }
}
