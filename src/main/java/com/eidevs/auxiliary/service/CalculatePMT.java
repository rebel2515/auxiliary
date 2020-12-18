/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.service;

import com.eidevs.auxiliary.payload.DummyPayload;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eisrael
 */
public class CalculatePMT {

    private double calculateSimpleInterest(double rate, double noOfPayments, double presentValue) {
        double percent = 100;
        double S_Iint = (rate * noOfPayments * presentValue) / percent;
        return S_Iint;
    }

    private double monthlyInterest(double SI, double noOfPayments) {
        double M_Int = SI / noOfPayments;
        return M_Int;
    }

    //This will return the PMT for the current month
    private double calculatePMT(double rate, double noOfPayments, double presentValue) {
        double a = (1 + (rate / 12));
        double b = (-(noOfPayments / 12) * 12);
        double PMT = (presentValue * (rate / 12)) / (1 - Math.pow(a, b));
        return PMT;
    }

    public Object repaymentSchedule(double rate, double noOfPayments, double presentValue) {
        List<DummyPayload> pmt = new ArrayList<>();
        double PMT = 0; //variable for PMT
        double mInt = 0; //variable for monthly interest
        double sInt = 0; //variable for simple interest
        double mPrin = 0; //variable for monthly principal excluding interest
        double prinWithInt = 0; //variable for outstanding principal
        double pv = presentValue;
        String np = String.valueOf(noOfPayments);
        int i = Integer.valueOf(np);
        for (int j = 1; j <= i; j++) {
            DummyPayload dummy = new DummyPayload();
            //Calculate PMT
            PMT = calculatePMT(rate, noOfPayments, pv);
            
            //Calculate simple interest
            sInt = calculateSimpleInterest(rate, noOfPayments, pv);
            
            //total loan repayment
            prinWithInt = pv + sInt;
            
            //Monthly Interest
            mInt = monthlyInterest(sInt , noOfPayments);
            
            //calculate outstadin
            mPrin = PMT - mInt;
            
            //Add to list
            dummy.setMonthlyInterest(mInt);
            dummy.setMonthlyPrincipal(mPrin);
            dummy.setMonthlyRepayment(PMT);
            pmt.add(dummy);
            
            //Set outstanding principal
            pv = pv - mPrin;
            noOfPayments = noOfPayments - 1;
        }
        return pmt;
    }
}
