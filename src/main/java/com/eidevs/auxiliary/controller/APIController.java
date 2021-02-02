/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.controller;

import com.eidevs.auxiliary.exceptions.AuthorizationCredentialException;
import com.eidevs.auxiliary.service.ExtractionService;
import com.eidevs.auxiliary.service.GenericService;
import com.eidevs.auxiliary.service.MiddleWareService;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eisrael
 */
@RestController
@RequestMapping("/api/service")
public class APIController {

    @Autowired
    ExtractionService extractionService;
    @Autowired
    MiddleWareService middleWareService;
    @Autowired
    GenericService genericService;
    RestTemplate restTemplate;
    Gson gson;

    public APIController() {
        restTemplate = new RestTemplate();
        gson = new Gson();
    }

    @GetMapping("/users/disable")
    public String disableUsers() {
        String result = extractionService.updateUserStatus();
        return result;
    }

    @GetMapping("/biller/bouquet")
    public String updateBillers() {
        String result = extractionService.createBillerPackage();
        return result;
    }

    @GetMapping("/ft/sort")
    public String sortFT() {
        String result = extractionService.sortFT();
        return result;
    }

//    @GetMapping("/stmt/update")
//    public String stmtUpdate() {
//        String result = extractionService.updateStmtRecordWithClosingBalance();
//        return result;
//    }
    @GetMapping("/stmt/update/opening-balance")
    public String stmtUpdateOpeningBalance() {
        String result = extractionService.updateStmtRecordWithOpeningBalance();
        return result;
    }

    @PostMapping(value = "/txn/withdrawal", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String bvnRequest(@RequestHeader(value = "Authorization") String authorization, @RequestBody String requestPayload, HttpServletRequest request) throws Exception {
        Boolean requestHeaderValid = middleWareService.checkRequestHeaderValidity(authorization);
        if (!requestHeaderValid) {
            throw new AuthorizationCredentialException();
        }

        return middleWareService.Withdrawal(requestPayload);
    }

    @PostMapping(value = "/hashValue", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String hashValue(@RequestBody String requestBody, HttpServletRequest request) {
        return genericService.hashKey(requestBody);
    }

    @PostMapping(value = "/account-opening", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String onlineAccountOpening(@RequestHeader(value = "Authorization") String authorization, @RequestBody String requestPayload, HttpServletRequest request) throws Exception {
        Boolean requestHeaderValid = middleWareService.checkRequestHeaderValidity(authorization);
        if (!requestHeaderValid) {
            throw new AuthorizationCredentialException();
        }

        return middleWareService.onlineAccountOpening(requestPayload);
    }

    @PostMapping(value = "/update/temp/till-transfer", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String updateTellerTemp(@RequestHeader(value = "Authorization") String authorization, @RequestBody String requestPayload, HttpServletRequest request) throws Exception {
        Boolean requestHeaderValid = middleWareService.checkRequestHeaderValidity(authorization);
        if (!requestHeaderValid) {
            throw new AuthorizationCredentialException();
        }

        return middleWareService.updateTellerTemp(requestPayload);
    }

    @GetMapping("/update/teller-history")
    public String updateTellerHistory() {
        String result = middleWareService.updateTellerHistory();
        return result;
    }
    
    @PostMapping(value = "/write/customer-details", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String writeCustomerDetails(@RequestHeader(value = "Authorization") String authorization, @RequestBody String requestPayload, HttpServletRequest request) throws Exception {
        Boolean requestHeaderValid = middleWareService.checkRequestHeaderValidity(authorization);
        if (!requestHeaderValid) {
            throw new AuthorizationCredentialException();
        }

        return middleWareService.writeCustomerDetail(requestPayload);
    }
    
    @GetMapping("/users/aml")
    public String createAMLUsers() {
        String result = extractionService.createAMLUsers();
        return result;
    }
}
