/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.payload;

import java.time.LocalDateTime;

/**
 *
 * @author Brian A. Okon okon.brian@gmail.com
 */
public class AccountPayload {

    private String accountNumber;
    private String accountOfficer;
    private String accountOfficerDesc;
    private String savingsOfficer;
    private String availableBalance;
    private String ledgerBalance;
    private String tranRef;
    private String accountRestriction;
    private String accountStatus;
    private String customerId;
    private String accountName;
    private String accountCode;
    private String currency;
    private String branchCode;
    private String branchName;
    private String branch;
    private String accountType;
    private String productCode;
    private String accountDescription;
    private String productDescription;
    private String t24AccountNumber;
    private String openingDate;
    private String noOfTrans;
    private String nubanAccountNumber;
    private String companyCode;
    private String deptCode;
    private String accountMnemonics;
    private String inputter;
    private String authoriser;
    private String blockedReason;
    private String gender;
    private String address;
    private String r05Account;
    private String phoneNo;
    private String age;
    private String introducerCif;
    private String introducerName;
    private String businessAddress;
    private String kyc;
    private String bvn;
    private String accountlocation;
    private String association;
    private String city;
    private String state;
    private String category;
    private String categoryDesc;
    private String dividendCalc;
    private String taxPayable;
    private String passbook;
    private String settlementAccount;
    private String closingDate;
    private String closingChargeType;
    private String closingChargeAmount;
    private String closeOnline;
    private String openClearedBalance;
    private String onlineActualBalance;
    private String onlineClearedBalance;
    private String standingOrder;
    private String unclearedEntries;
    private String totalPendingDebit;
    private String totalPendingCharge;
    private String totalPendingTax;
    private String limitReference;

    //Cheque Issue Fields
    private String chequeClearing;
    private String chequeNoLeaves;
    private String chequeStatus;
    private String chequeIssueDate;
    private String chequeNumberIssued;
    private String chequeNoStart;
    private String chequeWaiveCharges;
    private String chequeNotes;
    private String chequeCharges;
    private String chequeChgCode;
    private String chequeDateRequest;
    private String chequeId;
    private String chequeResult;
    private String transType;
    private String calculateDividend;
    private String status;
    private String insurable;
    private String alternateAccount;
    private String alternateAccountId;
    private String currentNumber;
    private String dateTime;

    //For audit purpose
    private String createdBy;
    private LocalDateTime createdAt;
    private String capitalDate;
    private String actualBalance;
    private String postingRestrict;
    private String chargeableAmount;
    private String totalAccountAmount;
    private String capitalizeInterest;
    private String override;
    private String postingRestrictDesc;
    private String resetDate;
    private String transRef;

    private String savingsPlan;
    private String savingsAmount;
    private String savingsStartDate;
    private String loanDisbusmentDate;
    private String loanId;
    private String loanStatus;
    private String sessionId;
    private String inputterFullName;
    private String authorizerEmail;
    private String categoryCode;
    
    public String getAccountOfficer() {
        return accountOfficer;
    }

    public void setAccountOfficer(String accountOfficer) {
        this.accountOfficer = accountOfficer;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getR05Account() {
        return r05Account;
    }

    public void setR05Account(String r05Account) {
        this.r05Account = r05Account;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIntroducerCif() {
        return introducerCif;
    }

    public void setIntroducerCif(String introducerCif) {
        this.introducerCif = introducerCif;
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

    public String getKyc() {
        return kyc;
    }

    public void setKyc(String kyc) {
        this.kyc = kyc;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getAccountlocation() {
        return accountlocation;
    }

    public void setAccountlocation(String accountlocation) {
        this.accountlocation = accountlocation;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSavingsOfficer() {
        return savingsOfficer;
    }

    public void setSavingsOfficer(String savingsOfficer) {
        this.savingsOfficer = savingsOfficer;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(String ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public String getTranRef() {
        return tranRef;
    }

    public void setTranRef(String tranRef) {
        this.tranRef = tranRef;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public String getT24AccountNumber() {
        return t24AccountNumber;
    }

    public void setT24AccountNumber(String t24AccountNumber) {
        this.t24AccountNumber = t24AccountNumber;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getNoOfTrans() {
        return noOfTrans;
    }

    public void setNoOfTrans(String noOfTrans) {
        this.noOfTrans = noOfTrans;
    }

    public String getNubanAccountNumber() {
        return nubanAccountNumber;
    }

    public void setNubanAccountNumber(String nubanAccountNumber) {
        this.nubanAccountNumber = nubanAccountNumber;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getAccountMnemonics() {
        return accountMnemonics;
    }

    public void setAccountMnemonics(String accountMnemonics) {
        this.accountMnemonics = accountMnemonics;
    }

    public String getInputter() {
        return inputter;
    }

    public void setInputter(String inputter) {
        this.inputter = inputter;
    }

    public String getAuthoriser() {
        return authoriser;
    }

    public void setAuthoriser(String authoriser) {
        this.authoriser = authoriser;
    }

    public String getBlockedReason() {
        return blockedReason;
    }

    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDividendCalc() {
        return dividendCalc;
    }

    public void setDividendCalc(String dividendCalc) {
        this.dividendCalc = dividendCalc;
    }

    public String getTaxPayable() {
        return taxPayable;
    }

    public void setTaxPayable(String taxPayable) {
        this.taxPayable = taxPayable;
    }

    public String getPassbook() {
        return passbook;
    }

    public void setPassbook(String passbook) {
        this.passbook = passbook;
    }

    public String getSettlementAccount() {
        return settlementAccount;
    }

    public void setSettlementAccount(String settlementAccount) {
        this.settlementAccount = settlementAccount;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getClosingChargeType() {
        return closingChargeType;
    }

    public void setClosingChargeType(String closingChargeType) {
        this.closingChargeType = closingChargeType;
    }

    public String getClosingChargeAmount() {
        return closingChargeAmount;
    }

    public void setClosingChargeAmount(String closingChargeAmount) {
        this.closingChargeAmount = closingChargeAmount;
    }

    public String getCloseOnline() {
        return closeOnline;
    }

    public void setCloseOnline(String closeOnline) {
        this.closeOnline = closeOnline;
    }

    public String getOpenClearedBalance() {
        return openClearedBalance;
    }

    public void setOpenClearedBalance(String openClearedBalance) {
        this.openClearedBalance = openClearedBalance;
    }

    public String getOnlineActualBalance() {
        return onlineActualBalance;
    }

    public void setOnlineActualBalance(String onlineActualBalance) {
        this.onlineActualBalance = onlineActualBalance;
    }

    public String getOnlineClearedBalance() {
        return onlineClearedBalance;
    }

    public void setOnlineClearedBalance(String onlineClearedBalance) {
        this.onlineClearedBalance = onlineClearedBalance;
    }

    public String getStandingOrder() {
        return standingOrder;
    }

    public void setStandingOrder(String standingOrder) {
        this.standingOrder = standingOrder;
    }

    public String getUnclearedEntries() {
        return unclearedEntries;
    }

    public void setUnclearedEntries(String unclearedEntries) {
        this.unclearedEntries = unclearedEntries;
    }

    public String getTotalPendingDebit() {
        return totalPendingDebit;
    }

    public void setTotalPendingDebit(String totalPendingDebit) {
        this.totalPendingDebit = totalPendingDebit;
    }

    public String getTotalPendingCharge() {
        return totalPendingCharge;
    }

    public void setTotalPendingCharge(String totalPendingCharge) {
        this.totalPendingCharge = totalPendingCharge;
    }

    public String getTotalPendingTax() {
        return totalPendingTax;
    }

    public void setTotalPendingTax(String totalPendingTax) {
        this.totalPendingTax = totalPendingTax;
    }

    public String getChequeClearing() {
        return chequeClearing;
    }

    public void setChequeClearing(String chequeClearing) {
        this.chequeClearing = chequeClearing;
    }

    public String getChequeNoLeaves() {
        return chequeNoLeaves;
    }

    public void setChequeNoLeaves(String chequeNoLeaves) {
        this.chequeNoLeaves = chequeNoLeaves;
    }

    public String getChequeStatus() {
        return chequeStatus;
    }

    public void setChequeStatus(String chequeStatus) {
        this.chequeStatus = chequeStatus;
    }

    public String getChequeIssueDate() {
        return chequeIssueDate;
    }

    public void setChequeIssueDate(String chequeIssueDate) {
        this.chequeIssueDate = chequeIssueDate;
    }

    public String getChequeNumberIssued() {
        return chequeNumberIssued;
    }

    public void setChequeNumberIssued(String chequeNumberIssued) {
        this.chequeNumberIssued = chequeNumberIssued;
    }

    public String getChequeNoStart() {
        return chequeNoStart;
    }

    public void setChequeNoStart(String chequeNoStart) {
        this.chequeNoStart = chequeNoStart;
    }

    public String getChequeWaiveCharges() {
        return chequeWaiveCharges;
    }

    public void setChequeWaiveCharges(String chequeWaiveCharges) {
        this.chequeWaiveCharges = chequeWaiveCharges;
    }

    public String getChequeNotes() {
        return chequeNotes;
    }

    public void setChequeNotes(String chequeNotes) {
        this.chequeNotes = chequeNotes;
    }

    public String getChequeCharges() {
        return chequeCharges;
    }

    public void setChequeCharges(String chequeCharges) {
        this.chequeCharges = chequeCharges;
    }

    public String getChequeChgCode() {
        return chequeChgCode;
    }

    public void setChequeChgCode(String chequeChgCode) {
        this.chequeChgCode = chequeChgCode;
    }

    public String getChequeDateRequest() {
        return chequeDateRequest;
    }

    public void setChequeDateRequest(String chequeDateRequest) {
        this.chequeDateRequest = chequeDateRequest;
    }

    public String getChequeId() {
        return chequeId;
    }

    public void setChequeId(String chequeId) {
        this.chequeId = chequeId;
    }

    public String getChequeResult() {
        return chequeResult;
    }

    public void setChequeResult(String chequeResult) {
        this.chequeResult = chequeResult;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLimitReference() {
        return limitReference;
    }

    public void setLimitReference(String limitReference) {
        this.limitReference = limitReference;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCalculateDividend() {
        return calculateDividend;
    }

    public void setCalculateDividend(String calculateDividend) {
        this.calculateDividend = calculateDividend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getInsurable() {
        return insurable;
    }

    public void setInsurable(String insurable) {
        this.insurable = insurable;
    }

    public String getAlternateAccount() {
        return alternateAccount;
    }

    public void setAlternateAccount(String alternateAccount) {
        this.alternateAccount = alternateAccount;
    }

    public String getAlternateAccountId() {
        return alternateAccountId;
    }

    public void setAlternateAccountId(String alternateAccountId) {
        this.alternateAccountId = alternateAccountId;
    }

    public String getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(String currentNumber) {
        this.currentNumber = currentNumber;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCapitalDate() {
        return capitalDate;
    }

    public void setCapitalDate(String capitalDate) {
        this.capitalDate = capitalDate;
    }

    public String getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(String actualBalance) {
        this.actualBalance = actualBalance;
    }

    public String getPostingRestrict() {
        return postingRestrict;
    }

    public void setPostingRestrict(String postingRestrict) {
        this.postingRestrict = postingRestrict;
    }

    public String getChargeableAmount() {
        return chargeableAmount;
    }

    public void setChargeableAmount(String chargeableAmount) {
        this.chargeableAmount = chargeableAmount;
    }

    public String getTotalAccountAmount() {
        return totalAccountAmount;
    }

    public void setTotalAccountAmount(String totalAccountAmount) {
        this.totalAccountAmount = totalAccountAmount;
    }

    public String getCapitalizeInterest() {
        return capitalizeInterest;
    }

    public void setCapitalizeInterest(String capitalizeInterest) {
        this.capitalizeInterest = capitalizeInterest;
    }

    public String getOverride() {
        return override;
    }

    public void setOverride(String override) {
        this.override = override;
    }

    public String getPostingRestrictDesc() {
        return postingRestrictDesc;
    }

    public void setPostingRestrictDesc(String postingRestrictDesc) {
        this.postingRestrictDesc = postingRestrictDesc;
    }

    public String getAccountOfficerDesc() {
        return accountOfficerDesc;
    }

    public void setAccountOfficerDesc(String accountOfficerDesc) {
        this.accountOfficerDesc = accountOfficerDesc;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public String getSavingsPlan() {
        return savingsPlan;
    }

    public void setSavingsPlan(String savingsPlan) {
        this.savingsPlan = savingsPlan;
    }

    public String getSavingsAmount() {
        return savingsAmount;
    }

    public void setSavingsAmount(String savingsAmount) {
        this.savingsAmount = savingsAmount;
    }

    public String getSavingsStartDate() {
        return savingsStartDate;
    }

    public void setSavingsStartDate(String savingsStartDate) {
        this.savingsStartDate = savingsStartDate;
    }

    public String getLoanDisbusmentDate() {
        return loanDisbusmentDate;
    }

    public void setLoanDisbusmentDate(String loanDisbusmentDate) {
        this.loanDisbusmentDate = loanDisbusmentDate;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getInputterFullName() {
        return inputterFullName;
    }

    public void setInputterFullName(String inputterFullName) {
        this.inputterFullName = inputterFullName;
    }

    public String getAuthorizerEmail() {
        return authorizerEmail;
    }

    public void setAuthorizerEmail(String authorizerEmail) {
        this.authorizerEmail = authorizerEmail;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

}
