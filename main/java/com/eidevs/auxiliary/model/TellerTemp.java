/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "teller_temp")
public class TellerTemp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;
    @Column(name = "teller_id_to")
    private String tellerIdTo;
    @Column(name = "teller_id_from")
    private String tellerIdFrom;
    @Column(name = "debit_account")
    private String debitAccount;
    @Column(name = "debit_account_name")
    private String debitAccountName;
    @Column(name = "debit_account_bvn")
    private String debitAccountBVN;
    @Column(name = "debit_account_kyc")
    private String debitAccountKYCLevel;
    @Column(name = "debit_account_bank")
    private String debitAccountBank;
    @Column(name = "credit_account")
    private String creditAccount;
    @Column(name = "credit_account_name")
    private String creditAccountName;
    @Column(name = "credit_account_inst_code")
    private String creditAccountInstCode;
    @Column(name = "credit_account_bvn")
    private String creditAccountBVN;
    @Column(name = "credit_account_kyc")
    private String creditAccountKYCLevel;
    @Column(name = "credit_account_bank")
    private String creditAccountBank;
    @Column(name = "currency")
    private String currency;
    @Column(name = "narration")
    private String narration;
    @Column(name = "ticket_number")
    private String ticketNumber;
    @Column(name = "trans_id")
    private String transId;
    @Column(name = "amount")
    private String amount;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "available_balance")
    private String availableBalance;
    @Column(name = "amount_areas_advance")
    private String amountInArearsOrAdvance;
    @Column(name = "interest_due")
    private String interestDue;
    @Column(name = "deposit_slip_no")
    private String depositSlipNo;
    @Column(name = "collection_officer")
    private String collectionOfficer;
    @Column(name = "self_deposit")
    private String selfDeposit;
    @Column(name = "waive_charges")
    private String waiveCharges;
    @Column(name = "charges")
    private String charges;
    @Column(name = "charges_type")
    private String chargesType;
    @Column(name = "status")
    private String status;
    @Column(name = "trans_type")
    private String transType;
    @Column(name = "branch_code")
    private String branchCode;
    @Column(name = "branch")
    private String branch;
    @Column(name = "teller_id")
    private String tellerId;
    @Column(name = "teller_name")
    private String tellerName;
    @Column(name = "required_approval_level")
    private int requiredApprovalLevel;
    @Column(name = "current_approval_level")
    private int currentApprovalLevel;
    @Column(name = "status_message")
    private String statusMessage;
    @Column(name = "cheque_type")
    private String chequeType;
    @Column(name = "cheque_number")
    private String chequeNumber;
    @Column(name = "reject_reason")
    private String rejectReason;
    @Column(name = "drawer_cheque_no")
    private String drawerChequeNo;
    @Column(name = "drawer_account_no")
    private String drawerAcctNo;
    @Column(name = "drawer_branch_code")
    private String drawerBranchCode;
    @Column(name = "drawer_name")
    private String drawerName;
    @Column(name = "sign_instruction")
    private String signInstructions;
    @Column(name = "credit_amount")
    private String creditAmount;
    @Column(name = "charge_account")
    private String chargeAccount;
    @Column(name = "charge_amount")
    private String chargeAmount;
    @Column(name = "xferCNo")
    private String xferCNo;
    @Column(name = "override")
    private String override;
    @Column(name = "channel_code")
    private String channelCode;
    @Column(name = "name_enquiry_ref")
    private String nameEnquiryRef;
    @Column(name = "session_id")
    private String sessionId;
    @Column(name = "inputter_fullname")
    private String inputterFullName;
    @Column(name = "authorizer_email")
    private String authorizerEmail;
    @Column(name = "ais_username")
    private String aisUsername;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "category_code")
    private String categoryCode;
    @Column(name = "customer_type")
    private String customerType;
    @Column(name = "email_sent")
    private String emailSent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTellerIdTo() {
        return tellerIdTo;
    }

    public void setTellerIdTo(String tellerIdTo) {
        this.tellerIdTo = tellerIdTo;
    }

    public String getTellerIdFrom() {
        return tellerIdFrom;
    }

    public void setTellerIdFrom(String tellerIdFrom) {
        this.tellerIdFrom = tellerIdFrom;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getDebitAccountName() {
        return debitAccountName;
    }

    public void setDebitAccountName(String debitAccountName) {
        this.debitAccountName = debitAccountName;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getCreditAccountName() {
        return creditAccountName;
    }

    public void setCreditAccountName(String creditAccountName) {
        this.creditAccountName = creditAccountName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getAmountInArearsOrAdvance() {
        return amountInArearsOrAdvance;
    }

    public void setAmountInArearsOrAdvance(String amountInArearsOrAdvance) {
        this.amountInArearsOrAdvance = amountInArearsOrAdvance;
    }

    public String getInterestDue() {
        return interestDue;
    }

    public void setInterestDue(String interestDue) {
        this.interestDue = interestDue;
    }

    public String getDepositSlipNo() {
        return depositSlipNo;
    }

    public void setDepositSlipNo(String depositSlipNo) {
        this.depositSlipNo = depositSlipNo;
    }

    public String getCollectionOfficer() {
        return collectionOfficer;
    }

    public void setCollectionOfficer(String collectionOfficer) {
        this.collectionOfficer = collectionOfficer;
    }

    public String getSelfDeposit() {
        return selfDeposit;
    }

    public void setSelfDeposit(String selfDeposit) {
        this.selfDeposit = selfDeposit;
    }

    public String getWaiveCharges() {
        return waiveCharges;
    }

    public void setWaiveCharges(String waiveCharges) {
        this.waiveCharges = waiveCharges;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getChargesType() {
        return chargesType;
    }

    public void setChargesType(String chargesType) {
        this.chargesType = chargesType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
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

    public String getTellerId() {
        return tellerId;
    }

    public void setTellerId(String tellerId) {
        this.tellerId = tellerId;
    }

    public String getTellerName() {
        return tellerName;
    }

    public void setTellerName(String tellerName) {
        this.tellerName = tellerName;
    }

    public int getRequiredApprovalLevel() {
        return requiredApprovalLevel;
    }

    public void setRequiredApprovalLevel(int requiredApprovalLevel) {
        this.requiredApprovalLevel = requiredApprovalLevel;
    }

    public int getCurrentApprovalLevel() {
        return currentApprovalLevel;
    }

    public void setCurrentApprovalLevel(int currentApprovalLevel) {
        this.currentApprovalLevel = currentApprovalLevel;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getChequeType() {
        return chequeType;
    }

    public void setChequeType(String chequeType) {
        this.chequeType = chequeType;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getDrawerChequeNo() {
        return drawerChequeNo;
    }

    public void setDrawerChequeNo(String drawerChequeNo) {
        this.drawerChequeNo = drawerChequeNo;
    }

    public String getDrawerAcctNo() {
        return drawerAcctNo;
    }

    public void setDrawerAcctNo(String drawerAcctNo) {
        this.drawerAcctNo = drawerAcctNo;
    }

    public String getDrawerBranchCode() {
        return drawerBranchCode;
    }

    public void setDrawerBranchCode(String drawerBranchCode) {
        this.drawerBranchCode = drawerBranchCode;
    }

    public String getDrawerName() {
        return drawerName;
    }

    public void setDrawerName(String drawerName) {
        this.drawerName = drawerName;
    }

    public String getSignInstructions() {
        return signInstructions;
    }

    public void setSignInstructions(String signInstructions) {
        this.signInstructions = signInstructions;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getChargeAccount() {
        return chargeAccount;
    }

    public void setChargeAccount(String chargeAccount) {
        this.chargeAccount = chargeAccount;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getXferCNo() {
        return xferCNo;
    }

    public void setXferCNo(String xferCNo) {
        this.xferCNo = xferCNo;
    }

    public String getOverride() {
        return override;
    }

    public void setOverride(String override) {
        this.override = override;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getDebitAccountBVN() {
        return debitAccountBVN;
    }

    public void setDebitAccountBVN(String debitAccountBVN) {
        this.debitAccountBVN = debitAccountBVN;
    }

    public String getDebitAccountKYCLevel() {
        return debitAccountKYCLevel;
    }

    public void setDebitAccountKYCLevel(String debitAccountKYCLevel) {
        this.debitAccountKYCLevel = debitAccountKYCLevel;
    }

    public String getCreditAccountInstCode() {
        return creditAccountInstCode;
    }

    public void setCreditAccountInstCode(String creditAccountInstCode) {
        this.creditAccountInstCode = creditAccountInstCode;
    }

    public String getCreditAccountBVN() {
        return creditAccountBVN;
    }

    public void setCreditAccountBVN(String creditAccountBVN) {
        this.creditAccountBVN = creditAccountBVN;
    }

    public String getCreditAccountKYCLevel() {
        return creditAccountKYCLevel;
    }

    public void setCreditAccountKYCLevel(String creditAccountKYCLevel) {
        this.creditAccountKYCLevel = creditAccountKYCLevel;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getNameEnquiryRef() {
        return nameEnquiryRef;
    }

    public void setNameEnquiryRef(String nameEnquiryRef) {
        this.nameEnquiryRef = nameEnquiryRef;
    }

    public String getCreditAccountBank() {
        return creditAccountBank;
    }

    public void setCreditAccountBank(String creditAccountBank) {
        this.creditAccountBank = creditAccountBank;
    }

    public String getDebitAccountBank() {
        return debitAccountBank;
    }

    public void setDebitAccountBank(String debitAccountBank) {
        this.debitAccountBank = debitAccountBank;
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

    public String getAisUsername() {
        return aisUsername;
    }

    public void setAisUsername(String aisUsername) {
        this.aisUsername = aisUsername;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getEmailSent() {
        return emailSent;
    }

    public void setEmailSent(String emailSent) {
        this.emailSent = emailSent;
    }

}
