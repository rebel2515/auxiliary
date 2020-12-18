/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@Table(name = "teller")
public class Teller implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "teller_id")
    private String tellerId;
    @Column(name = "teller_name")
    private String tellerName;
    @Column(name = "status")
    private String status;
    @Column(name = "date_opened")
    private LocalDateTime dateOpened;
    @Column(name = "date_closed")
    private LocalDateTime dateClosed;
    @Column(name = "branch")
    private String branch;
    @Column(name = "branch_name")
    private String branchName;
    @Column(name = "in_use")
    private String inUse;
    @Column(name = "teller_balance")
    private BigDecimal tellerBalance;
    @Column(name = "teller_account")
    private String tellerAccount;
    @Column(name = "username")
    private String username;
    @Column(name = "cif")
    private String cif;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateOpened() {
        return dateOpened;
    }

    public void setDateOpened(LocalDateTime dateOpened) {
        this.dateOpened = dateOpened;
    }

    public LocalDateTime getDateClosed() {
        return dateClosed;
    }

    public void setDateClosed(LocalDateTime dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    public BigDecimal getTellerBalance() {
        return tellerBalance;
    }

    public void setTellerBalance(BigDecimal tellerBalance) {
        this.tellerBalance = tellerBalance;
    }

    public String getTellerAccount() {
        return tellerAccount;
    }

    public void setTellerAccount(String tellerAccount) {
        this.tellerAccount = tellerAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

}
