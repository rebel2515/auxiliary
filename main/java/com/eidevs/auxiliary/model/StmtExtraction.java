/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
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
@Table(name = "stmt_extraction_3Qtrs")
public class StmtExtraction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "stmt_id")
    private String stmtId;
    @Column(name = "value_date")
    private String valueDate;
    @Column(name = "booking_date")
    private String bookingDate;
    @Column(name = "transaction_reference")
    private String transactionReference;
    @Column(name = "narration")
    private String narration;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "account_name")
    private String accountName;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "category")
    private String category;
    @Column(name = "inputter")
    private String inputter;
    @Column(name = "authorizer")
    private String authorizer;
    @Column(name = "full_narration")
    private String fullNarration;
    @Column(name = "jv_number")
    private String jvNumber;
    @Column(name = "amount_1")
    private String amount1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStmtId() {
        return stmtId;
    }

    public void setStmtId(String stmtId) {
        this.stmtId = stmtId;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInputter() {
        return inputter;
    }

    public void setInputter(String inputter) {
        this.inputter = inputter;
    }

    public String getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(String authorizer) {
        this.authorizer = authorizer;
    }

    public String getFullNarration() {
        return fullNarration;
    }

    public void setFullNarration(String fullNarration) {
        this.fullNarration = fullNarration;
    }

    public String getJvNumber() {
        return jvNumber;
    }

    public void setJvNumber(String jvNumber) {
        this.jvNumber = jvNumber;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

}
