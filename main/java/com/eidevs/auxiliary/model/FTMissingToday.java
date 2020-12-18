/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.model;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "ft_missing_today")
public class FTMissingToday implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "trans_ref")
    private String transRef;
    @Column(name = "amount")
    private String amount;
    @Column(name = "narration")
    private String narration;
    @Column(name = "debit_account")
    private String debitAccount;
    @Column(name = "credit_account")
    private String creditAccount;
    @Column(name = "branch")
    private String branch;
    @Column(name = "status")
    private String status;
    @Column(name = "ais_inputter")
    private String aisInputter;
    @Column(name = "ais_authorizer")
    private String aisAuthorizer;
    @Column(name = "trans_type")
    private String transType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransRef() {
        return transRef;
    }

    public void setTransRef(String transRef) {
        this.transRef = transRef;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getAisInputter() {
        return aisInputter;
    }

    public void setAisInputter(String aisInputter) {
        this.aisInputter = aisInputter;
    }

    public String getAisAuthorizer() {
        return aisAuthorizer;
    }

    public void setAisAuthorizer(String aisAuthorizer) {
        this.aisAuthorizer = aisAuthorizer;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
