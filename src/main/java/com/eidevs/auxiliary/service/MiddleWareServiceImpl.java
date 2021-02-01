/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.service;

import com.eidevs.auxiliary.model.Teller;
import com.eidevs.auxiliary.model.TellerHistory;
import com.eidevs.auxiliary.model.TellerTemp;
import com.eidevs.auxiliary.model.customerAccount.T24Customers;
import com.eidevs.auxiliary.payload.MiddleWarePayload;
import com.eidevs.auxiliary.repository.ExtractionRepository;
import com.eidevs.auxiliary.repository.customerAccount.CustomerAccountRepository;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eisrael
 */
@Service
public class MiddleWareServiceImpl implements MiddleWareService {

    @Autowired
    GenericService genericService;
    @Autowired
    Environment env;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ExtractionRepository extractionRepository;
    @Autowired
    CustomerAccountRepository customerAccountRepository;
    Gson gson;
    RestTemplate restTemplate;

    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMdd");
    private static String AUTHORIZATION = "";
    private static String SIGNATURE = "";
    private static String TIMESTAMP = "";
    private static final Logger LOGGER = Logger.getLogger(MiddleWareServiceImpl.class.getName());

    public MiddleWareServiceImpl() {
        gson = new Gson();
        restTemplate = new RestTemplate();
    }

    @Override
    public Boolean checkRequestHeaderValidity(String authorization) {
        //Check if the request header is valid
        AUTHORIZATION = "Basic " + Base64.getEncoder().encodeToString((env.getProperty("api.authorization.username").trim() + ":" + env.getProperty("api.authorization.password").trim()).getBytes());
        return AUTHORIZATION.equals(authorization);
    }

    @Override
    public String Withdrawal(String requestBody) {
        MiddleWarePayload payload = gson.fromJson(requestBody, MiddleWarePayload.class);
        if (payload != null) {
            String transRef = genericService.generateTransRef("AUXI");
            try {
                String t24Date = genericService.getPostingDate();
                MiddleWarePayload withdrawal = new MiddleWarePayload();
                withdrawal.setAmount(payload.getAmount());
                withdrawal.setBranchCode("NG0010068");
                withdrawal.setCreditAccount(payload.getCreditAccount());
                withdrawal.setDebitAccount(payload.getDebitAccount());
                withdrawal.setNarration(payload.getNarration());
                withdrawal.setTransRef(payload.getTransRef());
                withdrawal.setAgentAccountNumber("1999101972");
                withdrawal.setDebitCurrency("NGN");

                String transRequest = gson.toJson(withdrawal, MiddleWarePayload.class);

                String middlewareResponse = genericService.endPointPostRequest("/generic/payment/withdrawal", transRequest,
                        env.getProperty("accion.middleware.production.username"),
                        env.getProperty("accion.middleware.production.password"));

                MiddleWarePayload transResponse = gson.fromJson(middlewareResponse, MiddleWarePayload.class);
                if (transResponse != null) {
                    MiddleWarePayload responseMessage = new MiddleWarePayload();
                    String returnMessage = "";
                    if (transResponse.getResponseCode().equals("51")) {
                        responseMessage.setStatus("Failed");
                        responseMessage.setTransRef(transResponse.getTransRef());
                        responseMessage.setResponseCode("51");
                        String responseBody = gson.toJson(responseMessage, MiddleWarePayload.class);
                        return responseBody;
                    }

                    if (transResponse.getResponseCode().equals("00")) {
                        responseMessage.setStatus("Success");
                        responseMessage.setTransRef(transResponse.getTransRef());
                        responseMessage.setResponseCode("00");
                        String responseBody = gson.toJson(responseMessage, MiddleWarePayload.class);
                        return responseBody;
                    }

                    if ("01".equals(transResponse.getResponseCode())
                            || "03".equals(transResponse.getResponseCode())
                            || "05".equals(transResponse.getResponseCode())
                            || "06".equals(transResponse.getResponseCode())
                            || "07".equals(transResponse.getResponseCode())
                            || "08".equals(transResponse.getResponseCode())
                            || "09".equals(transResponse.getResponseCode())
                            || "12".equals(transResponse.getResponseCode())
                            || "13".equals(transResponse.getResponseCode())
                            || "14".equals(transResponse.getResponseCode())
                            || "15".equals(transResponse.getResponseCode())
                            || "16".equals(transResponse.getResponseCode())
                            || "17".equals(transResponse.getResponseCode())
                            || "18".equals(transResponse.getResponseCode())
                            || "26".equals(transResponse.getResponseCode())
                            || "34".equals(transResponse.getResponseCode())
                            || "35".equals(transResponse.getResponseCode())
                            || "57".equals(transResponse.getResponseCode())
                            || "58".equals(transResponse.getResponseCode())
                            || "92".equals(transResponse.getResponseCode())
                            || "94".equals(transResponse.getResponseCode())
                            || "96".equals(transResponse.getResponseCode())) {
                        responseMessage.setStatus("Failed");
                        responseMessage.setTransRef(transResponse.getTransRef());
                        responseMessage.setResponseCode(transResponse.getResponseCode());
                        String responseBody = gson.toJson(responseMessage, MiddleWarePayload.class);
                        return responseBody;
                    }
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        }

        MiddleWarePayload response = new MiddleWarePayload();
        response.setResponseCode("99");
        response.setResponseMessage("Malformed Request Body");
        response.setStatus("Failed");
        String responseBody = gson.toJson(response, MiddleWarePayload.class);
        return responseBody;
    }

    @Override
    public String onlineAccountOpening(String requestBody) {
        MiddleWarePayload payload = gson.fromJson(requestBody, MiddleWarePayload.class);
        if (payload != null) {
            try {
                String t24Date = genericService.getPostingDate();
                MiddleWarePayload accountInfo = new MiddleWarePayload();
                accountInfo.setFirstName(payload.getFirstName());
                accountInfo.setMiddleName(payload.getMiddleName());
                accountInfo.setLastName(payload.getLastName());
                accountInfo.setGender(payload.getGender());
                accountInfo.setDateOfBirth(payload.getDateOfBirth());
                accountInfo.setPhotoImage(payload.getPhotoImage());
                accountInfo.setSignatureImage(payload.getSignatureImage());
                accountInfo.setBvn(payload.getBvn());
                accountInfo.setBranchCode("NG0010006");
                accountInfo.setProductCode("14");
                accountInfo.setReferrer("");
                accountInfo.setKycTier(payload.getKycTier());
                accountInfo.setMaritalStatus(payload.getMaritalStatus());
                accountInfo.setEmploymentStatus(payload.getEmploymentStatus());

                String transRequest = gson.toJson(accountInfo, MiddleWarePayload.class);

                String middlewareResponse = genericService.endPointPostRequest("/generic/account/openonline", transRequest,
                        env.getProperty("accion.middleware.production.username"),
                        env.getProperty("accion.middleware.production.password"));

                MiddleWarePayload transResponse = gson.fromJson(middlewareResponse, MiddleWarePayload.class);
                if (transResponse != null) {
                    MiddleWarePayload responseMessage = new MiddleWarePayload();
                    String returnMessage = "";
                    if (transResponse.getResponseCode().equals("51")) {
                        responseMessage.setStatus("Failed");
                        responseMessage.setTransRef(transResponse.getTransRef());
                        responseMessage.setResponseCode("51");
                        String responseBody = gson.toJson(responseMessage, MiddleWarePayload.class);
                        return responseBody;
                    }

                    if (transResponse.getResponseCode().equals("00")) {
                        responseMessage.setStatus("Success");
                        responseMessage.setTransRef(transResponse.getTransRef());
                        responseMessage.setResponseCode("00");
                        String responseBody = gson.toJson(responseMessage, MiddleWarePayload.class);
                        return responseBody;
                    }

                    if ("01".equals(transResponse.getResponseCode())
                            || "03".equals(transResponse.getResponseCode())
                            || "05".equals(transResponse.getResponseCode())
                            || "06".equals(transResponse.getResponseCode())
                            || "07".equals(transResponse.getResponseCode())
                            || "08".equals(transResponse.getResponseCode())
                            || "09".equals(transResponse.getResponseCode())
                            || "12".equals(transResponse.getResponseCode())
                            || "13".equals(transResponse.getResponseCode())
                            || "14".equals(transResponse.getResponseCode())
                            || "15".equals(transResponse.getResponseCode())
                            || "16".equals(transResponse.getResponseCode())
                            || "17".equals(transResponse.getResponseCode())
                            || "18".equals(transResponse.getResponseCode())
                            || "26".equals(transResponse.getResponseCode())
                            || "34".equals(transResponse.getResponseCode())
                            || "35".equals(transResponse.getResponseCode())
                            || "57".equals(transResponse.getResponseCode())
                            || "58".equals(transResponse.getResponseCode())
                            || "92".equals(transResponse.getResponseCode())
                            || "94".equals(transResponse.getResponseCode())
                            || "96".equals(transResponse.getResponseCode())) {
                        responseMessage.setStatus("Failed");
                        responseMessage.setTransRef(transResponse.getTransRef());
                        responseMessage.setResponseCode(transResponse.getResponseCode());
                        String responseBody = gson.toJson(responseMessage, MiddleWarePayload.class);
                        return responseBody;
                    }
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        }

        MiddleWarePayload response = new MiddleWarePayload();
        response.setResponseCode("99");
        response.setResponseMessage("Malformed Request Body");
        response.setStatus("Failed");
        String responseBody = gson.toJson(response, MiddleWarePayload.class);
        return responseBody;
    }

    @Override
    public String updateTellerTemp(String requestBody) {
        MiddleWarePayload payload = gson.fromJson(requestBody, MiddleWarePayload.class);
        String branchCode = payload.getBranchCode();
        String transId = payload.getTransId();

        TellerTemp existingRecord = extractionRepository.getRecordWithTransId(transId);
        if (existingRecord != null) {
            return "duplicate record";
        }

        String ofsRequest = "\"" + env.getProperty("a24core.teller.till.transfer.read").trim()
                + "," + env.getProperty("a24core.T24.inputter.login.credentials")
                + "/" + branchCode + ","
                + transId + "\"";

        LOGGER.log(Level.INFO, "OFS Request (Till Transfer) :{0}", ofsRequest);

        String response = genericService.ofsResponse(env.getProperty("a24core.live"), ofsRequest);
        if (response.contains("//1")) {
            TellerTemp temp = new TellerTemp();
            String debitAccount = genericService.getStringFromOFSResponse(response, "DEBIT.ACCT.NO:1:1");
            String creditAccount = genericService.getStringFromOFSResponse(response, "CREDIT.ACCT.NO:1:1");
            String transactionType = genericService.getStringFromOFSResponse(response, "TRANSACTION.TYPE:1:1");
            String tillId = "";
            if (creditAccount.startsWith("NGN")) {
                String[] creditAccountDetails = creditAccount.split("10000");
                tillId = creditAccountDetails[1].substring(0, 4);
            }

            if (debitAccount.startsWith("NGN")) {
                String[] debitAccountDetails = debitAccount.split("10000");
                tillId = debitAccountDetails[1].substring(0, 4);
            }

            boolean isVault = false;
            if (tillId.equals("9999")) {
                isVault = true;
            }

            if (debitAccount.startsWith("NGN") && creditAccount.startsWith("NGN")) {
                Teller teller = extractionRepository.getTellerRecord(branchCode, genericService.getStringFromOFSResponse(response, "DEBIT.ACCT.NO:1:1"));
                temp.setCreatedBy(teller.getTellerName());
                String[] dateTime = genericService.getStringFromOFSResponse(response, "DATE.TIME:1:1").split("\\s+");
                String[] time = dateTime[3].split(":");
                temp.setCreatedAt(LocalDateTime.of(Integer.valueOf(dateTime[2]), Month.DECEMBER, Integer.valueOf(dateTime[0]), Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2])));
                temp.setTransType("Till Transfer");
                temp.setTransId(transId);
                temp.setCurrency("NGN");
                temp.setStatus("Pending");
                temp.setBranchCode(branchCode);

                //Extract the teller Id From from Debit Account
                temp.setBranch(genericService.getBranchNameUsingCode(branchCode));
                String tellerFromAccount = genericService.getStringFromOFSResponse(response, "CREDIT.ACCT.NO:1:1");
                String[] tellerFromAccountSetup = tellerFromAccount.split("10000");
                String tellerIdFrom = tellerFromAccountSetup[1].substring(0, 4);

                //Extract the teller Id To from Credit Account
                String tellerToAccount = genericService.getStringFromOFSResponse(response, "DEBIT.ACCT.NO:1:1");
                String[] tellerToAccountSetup = tellerToAccount.split("10000");
                String tellerIdTo = tellerToAccountSetup[1].substring(0, 4);
                temp.setTellerIdTo(tellerIdTo);
                temp.setTellerIdFrom(tellerIdFrom);

                temp.setTellerName(genericService.getTellerName(tellerIdFrom, branchCode));
                temp.setAmount(genericService.getStringFromOFSResponse(response, "DEBIT.AMOUNT:1:1").replace(".00", ""));
                temp.setNarration(genericService.getStringFromOFSResponse(response, "PAYMENT.DETAILS:1:1"));
                temp.setInputterFullName("null null");

                TellerTemp createRecord = extractionRepository.createTempRecord(temp);
                return "completed";
            }

            if (!debitAccount.startsWith("NGN") && creditAccount.startsWith("NGN") && isVault == true) {
                Teller tellerDetails = extractionRepository.getTellerRecord(branchCode, genericService.getStringFromOFSResponse(response, "CREDIT.ACCT.NO:1:1"));
                String correspondingBank = extractionRepository.getCorrespondingBankName(debitAccount);
                Teller teller = extractionRepository.getTellerWithBranchCode(branchCode);
                temp.setCreatedBy(teller.getTellerName());
                String[] dateTime = genericService.getStringFromOFSResponse(response, "DATE.TIME:1:1").split("\\s+");
                String[] time = dateTime[3].split(":");
                temp.setCreatedAt(LocalDateTime.of(Integer.valueOf(dateTime[2]), Month.DECEMBER, Integer.valueOf(dateTime[0]), Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2])));
                temp.setTransType("Vault Out");
                temp.setCreditAccount(creditAccount);
                temp.setCreditAccountName(tellerDetails.getTellerName());
                temp.setDebitAccount(debitAccount);
                temp.setDebitAccountName(correspondingBank);
                temp.setTransId(transId);
                temp.setCurrency("NGN");
                temp.setStatus("Pending");
                temp.setBranchCode(branchCode);
                temp.setCurrentApprovalLevel(1);
                temp.setRequiredApprovalLevel(1);
                temp.setTicketNumber(genericService.getStringFromOFSResponse(response, "TICKET.NO:1:1"));
                temp.setAuthorizerEmail("bokon@accionmfb.com");
                temp.setBranch(genericService.getBranchNameUsingCode(branchCode));
                temp.setAmount(genericService.getStringFromOFSResponse(response, "DEBIT.AMOUNT:1:1").replace(".00", ""));
                temp.setNarration(genericService.getStringFromOFSResponse(response, "PAYMENT.DETAILS:1:1"));
                temp.setInputterFullName("null null");

                TellerTemp createRecord = extractionRepository.createTempRecord(temp);
                return "completed";
            }

            if (debitAccount.startsWith("NGN") && !creditAccount.startsWith("NGN") && isVault == true) {
                Teller tellerDetails = extractionRepository.getTellerRecord(branchCode, genericService.getStringFromOFSResponse(response, "DEBIT.ACCT.NO:1:1"));
                String correspondingBank = extractionRepository.getCorrespondingBankName(creditAccount);
                Teller teller = extractionRepository.getTellerWithBranchCode(branchCode);
                temp.setCreatedBy(teller.getTellerName());
                String[] dateTime = genericService.getStringFromOFSResponse(response, "DATE.TIME:1:1").split("\\s+");
                String[] time = dateTime[3].split(":");
                temp.setCreatedAt(LocalDateTime.of(Integer.valueOf(dateTime[2]), Month.DECEMBER, Integer.valueOf(dateTime[0]), Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2])));
                temp.setTransType("Vault In");
                temp.setCreditAccount(creditAccount);
                temp.setCreditAccountName(correspondingBank);
                temp.setDebitAccount(debitAccount);
                temp.setDebitAccountName(tellerDetails.getTellerName());
                temp.setTransId(transId);
                temp.setCurrency("NGN");
                temp.setStatus("Pending");
                temp.setBranchCode(branchCode);
                temp.setCurrentApprovalLevel(1);
                temp.setRequiredApprovalLevel(1);
                temp.setTicketNumber(genericService.getStringFromOFSResponse(response, "TICKET.NO:1:1"));
                temp.setAuthorizerEmail("bokon@accionmfb.com");
                temp.setBranch(genericService.getBranchNameUsingCode(branchCode));
                temp.setAmount(genericService.getStringFromOFSResponse(response, "DEBIT.AMOUNT:1:1").replace(".00", ""));
                temp.setNarration(genericService.getStringFromOFSResponse(response, "PAYMENT.DETAILS:1:1"));
                temp.setInputterFullName("null null");

                TellerTemp createRecord = extractionRepository.createTempRecord(temp);
                return "completed";
            }

            if (transactionType.equals("ACD1")) {
                Teller tellerDetails = extractionRepository.getTellerRecord(branchCode, debitAccount);
                Teller teller = extractionRepository.getTellerWithBranchCode(branchCode);
                temp.setCreatedBy(teller.getTellerName());
                String[] dateTime = genericService.getStringFromOFSResponse(response, "DATE.TIME:1:1").split("\\s+");
                String[] time = dateTime[3].split(":");
                temp.setCreatedAt(LocalDateTime.of(Integer.valueOf(dateTime[2]), Month.DECEMBER, Integer.valueOf(dateTime[0]), Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2])));

                temp.setTransType("Cash Deposit");
                temp.setCreditAccount(creditAccount);
                String creditAccountName = extractionRepository.getAccountName(creditAccount);
                temp.setCreditAccountName(creditAccountName);
                temp.setDebitAccount(debitAccount);
                temp.setDebitAccountName(tellerDetails.getTellerName());
                temp.setTransId(transId);
                temp.setCurrency("NGN");
                temp.setStatus("Pending");
                temp.setBranchCode(branchCode);
                temp.setCurrentApprovalLevel(1);
                temp.setRequiredApprovalLevel(1);
                temp.setTicketNumber(genericService.getStringFromOFSResponse(response, "TICKET.NO:1:1") == null ? "" : genericService.getStringFromOFSResponse(response, "TICKET.NO:1:1"));
                temp.setAuthorizerEmail("bokon@accionmfb.com");
                temp.setBranch(genericService.getBranchNameUsingCode(branchCode));
                temp.setAmount(genericService.getStringFromOFSResponse(response, "DEBIT.AMOUNT:1:1").replace(".00", ""));
                temp.setNarration(genericService.getStringFromOFSResponse(response, "PAYMENT.DETAILS:1:1"));
                temp.setInputterFullName("null null");

                TellerTemp createRecord = extractionRepository.createTempRecord(temp);
                return "completed";
            }

            if (transactionType.equals("ACW1")) {
                Teller tellerDetails = extractionRepository.getTellerRecord(branchCode, creditAccount);
                Teller teller = extractionRepository.getTellerWithBranchCode(branchCode);
                temp.setCreatedBy(teller.getTellerName());
                String[] dateTime = genericService.getStringFromOFSResponse(response, "DATE.TIME:1:1").split("\\s+");
                String[] time = dateTime[3].split(":");
                temp.setCreatedAt(LocalDateTime.of(Integer.valueOf(dateTime[2]), Month.DECEMBER, Integer.valueOf(dateTime[0]), Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2])));
                String narration = genericService.getStringFromOFSResponse(response, "PAYMENT.DETAILS:1:1").toLowerCase();
                if (narration.contains("deposit") || narration.contains("dep") || narration.contains("dp")) {
                    temp.setTransType("Cash Deposit");
                }

                temp.setTransType("Cash Withdrawal");
                temp.setCreditAccount(creditAccount);
                String debitAccountName = extractionRepository.getAccountName(debitAccount);
                temp.setCreditAccountName(tellerDetails.getTellerName());
                temp.setDebitAccount(debitAccount);
                temp.setDebitAccountName(debitAccountName);
                temp.setTransId(transId);
                temp.setCurrency("NGN");
                temp.setStatus("Pending");
                temp.setBranchCode(branchCode);
                temp.setCurrentApprovalLevel(1);
                temp.setRequiredApprovalLevel(1);
                temp.setTicketNumber(genericService.getStringFromOFSResponse(response, "TICKET.NO:1:1") == null ? "" : genericService.getStringFromOFSResponse(response, "TICKET.NO:1:1"));
                temp.setAuthorizerEmail("bokon@accionmfb.com");
                temp.setBranch(genericService.getBranchNameUsingCode(branchCode));
                temp.setAmount(genericService.getStringFromOFSResponse(response, "DEBIT.AMOUNT:1:1").replace(".00", ""));
                temp.setNarration(genericService.getStringFromOFSResponse(response, "PAYMENT.DETAILS:1:1"));
                temp.setInputterFullName("null null");

                TellerTemp createRecord = extractionRepository.createTempRecord(temp);
                return "completed";
            }
        }

        return response;
    }

    @Override
    public String updateTellerHistory() {
        List<TellerTemp> pendingTransactions = extractionRepository.getAllPendingTransactions();
        if (pendingTransactions != null) {
            for (TellerTemp tempTrans : pendingTransactions) {
                TellerHistory existingRecord = extractionRepository.getRecordFromHistoryWithTransId(tempTrans.getTransId());
                if (existingRecord != null) {
                    TellerTemp dropRecord = extractionRepository.dropTellerTemp(tempTrans);
                    LOGGER.log(Level.INFO, tempTrans.getTransId().concat("record already exist in history table"));
                } else {
                    String ofsRequest = "\"" + env.getProperty("a24core.teller.transaction.fetch").trim()
                            + "," + env.getProperty("a24core.T24.inputter.login.credentials")
                            + "/" + tempTrans.getBranchCode() + ","
                            + tempTrans.getTransId() + "\"";

                    LOGGER.log(Level.INFO, "OFS Request (Teller Transaction Read) :{0}", ofsRequest);
                    String response = genericService.ofsResponse(env.getProperty("a24core.live"), ofsRequest);
                    if (response.contains("//1")) {
                        TellerHistory history = new TellerHistory();
                        String authorizer = genericService.getStringFromOFSResponse(response, "AUTHORISER:1:1");
                        String recordStatus = genericService.getStringFromOFSResponse(response, "RECORD.STATUS:1:1");
                        if (!authorizer.equals("")) {
                            BeanUtils.copyProperties(tempTrans, history);
                            history.setId(null);
                            history.setStatus(recordStatus.equals("REVE") ? recordStatus : "Approved");
                            String[] dateTime = genericService.getStringFromOFSResponse(response, "DATE.TIME:1:1").split("\\s+");
                            String[] time = dateTime[3].split(":");
                            history.setApprovedAt(LocalDateTime.of(Integer.valueOf(dateTime[2]), fullMonth(dateTime[1]), Integer.valueOf(dateTime[0]), Integer.valueOf(time[0]), Integer.valueOf(time[1]), Integer.valueOf(time[2])));
                            history.setApprovedBy(authorizer);
                            TellerHistory createRecord = extractionRepository.createTellerHistory(history);

                            TellerTemp dropRecord = extractionRepository.dropTellerTemp(tempTrans);
                        }
                    }
                }
            }
            return "completed";
        }
        return "no record to process at this time";
    }

    @Override
    public String writeCustomerDetail(String requestBody) {
        MiddleWarePayload payload = gson.fromJson(requestBody, MiddleWarePayload.class);
        String customerId = payload.getCustomerId();
        T24Customers customer = new T24Customers();
        T24Customers cus = customerAccountRepository.getCustomerRecord(customerId);
        if (cus != null) {
            return "record already exist";
        }
        String ofsRequest = "\"" + env.getProperty("a24core.customer.details.read").trim()
                + "," + env.getProperty("a24core.T24.inputter.login.credentials")
                + "/" + "NG0010068" + ","
                + customerId + "\"";
        LOGGER.log(Level.INFO, "OFS Request (Customer Details Read) :{0}", ofsRequest);
        String response = genericService.ofsResponse(env.getProperty("a24core.live"), ofsRequest);
        if (response.contains("//1")) {
            String street = genericService.getStringFromOFSResponse(response, "STREET:1:1");
            String city = genericService.getStringFromOFSResponse(response, "SUBURB.TOWN:1:1");
            String state = genericService.getStringFromOFSResponse(response, "PROVINCE.STATE:1:1");
            String firstName = genericService.getStringFromOFSResponse(response, "SHORT.NAME:1:1");
            String lastName = genericService.getStringFromOFSResponse(response, "NAME.1:1:1");
            customer.setAccountOfficer(genericService.getStringFromOFSResponse(response, "ACCOUNT.OFFICER:1:1"));
            customer.setAddress(street.concat(" ").concat(city).concat(" ").concat(state));
            customer.setBranchCode(genericService.getStringFromOFSResponse(response, "COMPANY.BOOK:1:1"));
            customer.setBranchName(genericService.getBranchNameUsingCode(genericService.getStringFromOFSResponse(response, "COMPANY.BOOK:1:1")));
            customer.setBvn(genericService.getStringFromOFSResponse(response, "BVN:1:1"));
            customer.setCity(city);
            customer.setCustomerId(customerId);
            customer.setCustomerName(firstName.concat(" ").concat(lastName));
            customer.setCustomerRestriction(genericService.getStringFromOFSResponse(response, "POSTING.RESTRICTION:1:1"));
            String dob = genericService.getStringFromOFSResponse(response, "BIRTH.INCORP.DATE:1:1");
            customer.setDob(dob.substring(0, 4).concat("/").concat(dob.substring(4, 6)).concat("/").concat(dob.substring(6, dob.length())));
            customer.setEmail(genericService.getStringFromOFSResponse(response, "EMAIL.1:1:1"));
            customer.setFirstName(firstName);
            customer.setGender(genericService.getStringFromOFSResponse(response, "GENDER:1:1"));
            customer.setKycTier(genericService.getStringFromOFSResponse(response, "KYC.LEVEL:1:1"));
            customer.setLastName(lastName);
            customer.setMiddleName("");
            customer.setMobileNumber(genericService.getStringFromOFSResponse(response, "TEL.MOBILE:1:1"));
            customer.setState(state);
            customer.setStreet(street);

            T24Customers createCustomer = customerAccountRepository.createCustomer(customer);
            return "completed";
        } else {
            return response;
        }
    }

    private Month fullMonth(String shortMonth) {
        switch (shortMonth) {
            case "JAN": {
                return Month.JANUARY;
            }
            case "FEB": {
                return Month.FEBRUARY;
            }
            case "MAR": {
                return Month.MARCH;
            }
            case "APR": {
                return Month.APRIL;
            }
            case "MAY": {
                return Month.MAY;
            }
            case "JUN": {
                return Month.JUNE;
            }
            case "JUL": {
                return Month.JULY;
            }
            case "AUG": {
                return Month.AUGUST;
            }
            case "SEP": {
                return Month.SEPTEMBER;
            }
            case "OCT": {
                return Month.OCTOBER;
            }
            case "NOV": {
                return Month.NOVEMBER;
            }
            default: {
                return Month.DECEMBER;
            }
        }
    }
}
