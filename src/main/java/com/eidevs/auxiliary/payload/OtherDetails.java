/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.payload;

/**
 *
 * @author bokon
 */
public class OtherDetails {

    private String phoneNo;
    private String customerName;
    private String amount;
    private String charge;

    public OtherDetails() {
    }

    public OtherDetails(String phoneNo, String customerName) {
        this.phoneNo = phoneNo;
        this.customerName = customerName;
    }

    public OtherDetails(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public OtherDetails(String phoneNo, String amount, String charge) {
        this.phoneNo = phoneNo;
        this.amount = amount;
        this.charge = charge;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }


}
