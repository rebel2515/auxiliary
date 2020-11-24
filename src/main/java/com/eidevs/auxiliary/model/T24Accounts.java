/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bokon
 */
@Entity
@Table(name = "t24_accounts")
public class T24Accounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "[Account No]")
    private String accountNumber;
    @Column(name = "[R05 Account No]")
    private String r05AccountNumber;
    @Column(name = "[Customer Name]")
    private String customerName;
    @Column(name = "[Account Officer]")
    private String accountOfficer;
    @Column(name = "[Collection Officer]")
    private String collectionOfficer;
    @Column(name = "Address", length = 5000)
    private String address;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "Category")
    private String category;
    @Column(name = "Currency")
    private String currency;
    @Column(name = "[Online Balance]")
    private String onlineBalance;
    @Column(name = "[Open Actual Balance]")
    private String openActualBalance;
    @Column(name = "[Phone Number]")
    private String phoneNumber;
    @Column(name = "[Open Date]")
    private String openDate;
    @Column(name = "[Customer No]")
    private String customerNumber;
    @Column(name = "AGE")
    private String age;
    @Column(name = "Branch")
    private String branch;
    @Column(name = "[Introducer CIF]")
    private String introducerCIF;
    @Column(name = "[Introducer Name]")
    private String introducerName;
    @Column(name = "[Business Address]", length = 5000)
    private String businessAddress;
    @Column(name = "[KYC Tier]")
    private String kycTier;
    @Column(name = "BVN")
    private String bvn;
    @Column(name = "[Location of account Open]")
    private String locationOfAccountOpen;
    @Column(name = "[Name of Association]")
    private String nameOfAssociation;
    @Column(name = "[Business City]")
    private String businessCity;
    @Column(name = "[Business State]")
    private String businessState;
    @Column(name = "[Branch Mne]")
    private String branchMnemonic;
    @Column(name = "[Nuban Account No]")
    private String nubanAccountNumber;
    @Column(name = "Signature")
    private String signature;
    @Column(name = "Passport")
    private String passport;
    @Column(name = "DOB")
    private String dob;
    @Column(name = "email")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getR05AccountNumber() {
        return r05AccountNumber;
    }

    public void setR05AccountNumber(String r05AccountNumber) {
        this.r05AccountNumber = r05AccountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountOfficer() {
        return accountOfficer;
    }

    public void setAccountOfficer(String accountOfficer) {
        this.accountOfficer = accountOfficer;
    }

    public String getCollectionOfficer() {
        return collectionOfficer;
    }

    public void setCollectionOfficer(String collectionOfficer) {
        this.collectionOfficer = collectionOfficer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOnlineBalance() {
        return onlineBalance;
    }

    public void setOnlineBalance(String onlineBalance) {
        this.onlineBalance = onlineBalance;
    }

    public String getOpenActualBalance() {
        return openActualBalance;
    }

    public void setOpenActualBalance(String openActualBalance) {
        this.openActualBalance = openActualBalance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIntroducerCIF() {
        return introducerCIF;
    }

    public void setIntroducerCIF(String introducerCIF) {
        this.introducerCIF = introducerCIF;
    }

    public String getIntroducerName() {
        return introducerName;
    }

    public void setIntroducerName(String introducerName) {
        this.introducerName = introducerName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getKycTier() {
        return kycTier;
    }

    public void setKycTier(String kycTier) {
        this.kycTier = kycTier;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getLocationOfAccountOpen() {
        return locationOfAccountOpen;
    }

    public void setLocationOfAccountOpen(String locationOfAccountOpen) {
        this.locationOfAccountOpen = locationOfAccountOpen;
    }

    public String getNameOfAssociation() {
        return nameOfAssociation;
    }

    public void setNameOfAssociation(String nameOfAssociation) {
        this.nameOfAssociation = nameOfAssociation;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBranchMnemonic() {
        return branchMnemonic;
    }

    public void setBranchMnemonic(String branchMnemonic) {
        this.branchMnemonic = branchMnemonic;
    }

    public String getNubanAccountNumber() {
        return nubanAccountNumber;
    }

    public void setNubanAccountNumber(String nubanAccountNumber) {
        this.nubanAccountNumber = nubanAccountNumber;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
