/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.payload;

/**
 *
 * @author eisrael
 */
public class DummyPayload {
    private double monthlyPrincipal;
    private double monthlyInterest;
    private double monthlyRepayment;

    public double getMonthlyPrincipal() {
        return monthlyPrincipal;
    }

    public void setMonthlyPrincipal(double monthlyPrincipal) {
        this.monthlyPrincipal = monthlyPrincipal;
    }

    public double getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(double monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }
}
