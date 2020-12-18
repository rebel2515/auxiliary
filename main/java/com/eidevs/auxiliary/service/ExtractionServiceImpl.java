/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.service;

import com.eidevs.auxiliary.model.AccountNumberDump;
import com.eidevs.auxiliary.model.DisableUsers;
import com.eidevs.auxiliary.model.FTMissingToday;
import com.eidevs.auxiliary.model.FTRecord;
import com.eidevs.auxiliary.model.StmtExtraction;
import com.eidevs.auxiliary.model.StmtExtractionOpeningBal;
import com.eidevs.auxiliary.model.Users;
import com.eidevs.auxiliary.model.XpressPayBillerPackage;
import com.eidevs.auxiliary.payload.AccountPayload;
import com.eidevs.auxiliary.payload.ListItems;
import com.eidevs.auxiliary.payload.ProductFields;
import com.eidevs.auxiliary.repository.ExtractionRepository;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author eisrael
 */
@Service
public class ExtractionServiceImpl implements ExtractionService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    Environment env;
    @Autowired
    GenericService genericService;
    @Autowired
    ExtractionRepository extractionRepository;
    Gson gson;

    private final String BASE_URL = "https://reseller.payxpress.com/api";
    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
    private static final Logger LOGGER = Logger.getLogger(ExtractionServiceImpl.class.getName());
    RestTemplate restTemplate;

    public ExtractionServiceImpl() {
        gson = new Gson();
        restTemplate = new RestTemplate();
    }

    @Override
    public String updateUserStatus() {
        List<DisableUsers> users = extractionRepository.getListOfUsersToDisable();
        if (users != null) {
            users.forEach((DisableUsers user) -> {
                Users userDetails = extractionRepository.getUserWithUsername(user.getUsername());
                if (userDetails != null) {
                    if (userDetails.getStatus().equalsIgnoreCase("Enabled")) {
                        userDetails.setStatus("Disabled");
                        extractionRepository.updateUserStatus(userDetails);
                        LOGGER.log(Level.INFO, "User ".concat(user.getUsername()).concat(" has been disabled"));
                    }
                }
            });
        }
        return "Completed";
    }

    @Override
    public String createBillerPackage() {
        String productId = env.getProperty("product.dstv.id").trim();
        URI url = URI.create(BASE_URL.concat("/productfields/").concat(productId));
        //Set required headers
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", genericService.getAuthorizationHeader(env.getProperty("xpresspay.authorization.username").trim(), env.getProperty("xpresspay.authorization.password").trim()));
        header.set("webkey", env.getProperty("xpresspay.webkey.live").trim());
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> parameterRequestEntity = new HttpEntity<>(header);
        Object rawResult = restTemplate.exchange(url, HttpMethod.GET, parameterRequestEntity, String.class);
        String fields[] = rawResult.toString().split("00,");
        String Str[] = fields[1].split("\\]\\,\\[");
        String result = Str[0].concat("]");
        ProductFields[] responsePayload = gson.fromJson(result, ProductFields[].class);
        String[] dstvBouquet;
        List<ListItems> listItems = null;
        if (responsePayload != null) {
            ProductFields productField = responsePayload[4];
            if (productField != null) {
                listItems = productField.getListItems();
                if (listItems != null) {
                    dstvBouquet = new String[listItems.size()];
                    int i = 0;
                    for (ListItems item : listItems) {
                        if (i < listItems.size()) {
                            dstvBouquet[i] = item.getItemName();
                            i++;
                        }
                    }
                }
            }
        }
        String[] createdBouquet = null;
        StringBuilder strings = new StringBuilder();
        int i = 0;
        for (ListItems item : listItems) {
            if (item != null) {
                String amount = "";
                String biller = "DSTV";
                String billerId = "1136";
                String bouquet = "";
                String packageName = "";
                String prodId = "";
                String status = "Active";
                String itemDesc[] = item.getItemName().split("\\s+");
                if (itemDesc[0].contains("Compact")) {
                    amount = item.getAmount();
                    bouquet = "Compact";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }
                if (itemDesc[0].contains("Yanga")) {
                    amount = item.getAmount();
                    bouquet = "Yanga";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }
                if (itemDesc[0].contains("Premium")) {
                    amount = item.getAmount();
                    bouquet = "Premium";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }
                if (itemDesc[0].contains("Asian")) {
                    amount = item.getAmount();
                    bouquet = "Asia";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }
                if (itemDesc[0].contains("Access")) {
                    amount = item.getAmount();
                    bouquet = "Access";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }
                if (itemDesc[0].contains("French")) {
                    amount = item.getAmount();
                    bouquet = "French";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }
                if (itemDesc[0].contains("Padi")) {
                    amount = item.getAmount();
                    bouquet = "Padi";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }
                if (itemDesc[0].contains("Confam")) {
                    amount = item.getAmount();
                    bouquet = "Confam";
                    packageName = item.getItemName();
                    prodId = item.getItemType();
                }

                XpressPayBillerPackage billerPackage = new XpressPayBillerPackage();
                billerPackage.setAmount(amount);
                billerPackage.setBiller(biller);
                billerPackage.setBillerId(billerId);
                billerPackage.setBouquet(bouquet);
                billerPackage.setPackageName(packageName);
                billerPackage.setProductId(prodId);
                billerPackage.setStatus(status);
                try {
                    extractionRepository.createBillerPackage(billerPackage);
                    strings.append(packageName).append("|");
                    LOGGER.log(Level.INFO, "CREATED BILLER PACKAGE - ".concat(packageName));
                } catch (Exception ex) {
                    LOGGER.log(Level.INFO, "ERROR OCCURED - ".concat(ex.getMessage()));
                    return ex.getMessage();
                }
            }
            i++;
        }
        return strings.toString();
    }

    @Override
    public String sortFT() {
        List<FTRecord> allCashTransactions = extractionRepository.getAllAISTransaction();
        if (allCashTransactions != null) {
            for (FTRecord t : allCashTransactions) {
                String ofsRequest = "\"" + env.getProperty("a24core.teller.transaction.fetch").trim()
                        + "," + env.getProperty("a24core.T24.inputter.login.credentials")
                        + "/" + "NG0010001" + ","
                        + t.getTransRef() + "\"";

                //Check where the environment is pointed to
                String response = genericService.ofsResponse(env.getProperty("a24core.live"), ofsRequest);
                if (response == null) {
                    return messageSource.getMessage("appMessages.invalidMenuPointer", new Object[0], Locale.ENGLISH);
                }

                //Check if there are errors 
                String validationResponse = genericService.validateResponse(response);
                if (validationResponse != null) {
                    return validationResponse;
                }

                if (response.contains("//1")) {
                    FTMissingToday ft = new FTMissingToday();
                    ft.setAisAuthorizer(genericService.getStringFromOFSResponse(response, "AUTHORISER:1:1"));
                    ft.setAisInputter(genericService.getStringFromOFSResponse(response, "INPUTTER:1:1"));
                    ft.setAmount(genericService.getStringFromOFSResponse(response, "LOC.AMT.DEBITED:1:1"));
                    ft.setBranch(genericService.getStringFromOFSResponse(response, "CO.CODE:1:1").replace("-", ""));
                    ft.setCreditAccount(genericService.getStringFromOFSResponse(response, "CREDIT.ACCT.NO:1:1"));
                    ft.setDebitAccount(genericService.getStringFromOFSResponse(response, "DEBIT.ACCT.NO:1:1"));
                    ft.setNarration(genericService.getStringFromOFSResponse(response, "ORDERING.CUST:1:1"));
                    ft.setStatus(genericService.getStringFromOFSResponse(response, "RECORD.STATUS:1:1"));
                    ft.setTransRef(genericService.getT24TransIdFromResponse(response));
                    ft.setTransType(genericService.getStringFromOFSResponse(response, "TRANSACTION.TYPE:1:1"));
                    extractionRepository.createMissingFTRecord(ft);
                    t.setProcessed(true);
                    extractionRepository.updateFT(t);
                }
            }
        }
        return "Completed";
    }

    private AccountPayload getAccountDetails(String accountNumber) {
        AccountPayload detailsRequest = new AccountPayload();
        detailsRequest.setAccountNumber(accountNumber);
        detailsRequest.setTranRef("mob1234567899");
        String requestJson = gson.toJson(detailsRequest);
        String response = "";
        //Get from the environment where the menu is pointed to
        switch (env.getProperty("a24core.accountDetails")) {
            case "Production":
                response = genericService.endPointPostRequest("/generic/account/details", requestJson,
                        env.getProperty("a24core.middleware.production.username"),
                        env.getProperty("a24core.middleware.production.password"));
                break;
            case "Staging":
                response = genericService.endPointPostRequest("/generic/account/details", requestJson,
                        env.getProperty("a24core.middleware.staging.username"),
                        env.getProperty("a24core.middleware.staging.password"));
                break;
            default:
                return null;
        }

        AccountPayload responsePayload = gson.fromJson(response, AccountPayload.class);
        return responsePayload;
    }

//    @Override
//    public String updateStmtRecordWithClosingBalance() {
//        List<StmtExtraction> records = extractionRepository.getAllRecordWhereAmountIsNull();
//        if (records != null) {
//            for (StmtExtraction rec : records) {
//                String response = "";
//                String ofsRequest = "";
//                String accountNumber = rec.getAccountNumber();
//                String category = rec.getCategory();
//                String startDate = "20200101";
//                String endDate = "20200930";
//
//                ofsRequest = "\"" + "ENQUIRY.SELECT,," + env.getProperty("a24core.T24.inputter.login.credentials").trim()
//                        + "," + env.getProperty("a24core.account.statement").trim() + ":EQ=" + accountNumber
//                        + ",BOOKING.DATE:RG=" + startDate + " " + endDate
//                        + "\"";
//
//                //Check the environment pointed to
//                response = genericService.ofsResponse(env.getProperty("a24core.accountStatement"), ofsRequest);
//                if (response == null) {
//                    return messageSource.getMessage("appMessages.invalidMenuPointer", new Object[0], Locale.ENGLISH);
//                }
//
//                LOGGER.log(Level.INFO, "RESPONSE FOR {0} : ".concat(response), new Object[]{accountNumber});
//
//                //Split the transactions
//                //Split the string into 2 and remove the header
//                LOGGER.log(Level.INFO, "Account Statement for - ".concat(rec.getAccountNumber()));
//                String[] splitString = response.split("JV.NO::JV.NO,");
//                if (splitString.length == 2) {
//                    String[] headerString = splitString[1].replace("\\t\\", "*").replace("\\B\\", "").replace("\\\",\\\",\\\"", "|").split("\\|");
//                    if (headerString.length == 1) {
//                        if (!headerString[0].contains("NO RECORDS RETURNED BY ROUTINE BASED SELECTION")) {
//                            String stringReplace = headerString[0].replace(",\\\"", "|").replace("\\", "").replace("\"", "");
//                            String[] detailsSplitString = stringReplace.split("\\|");
//
//                            for (int i = 0; i < detailsSplitString.length; i = i + 1) {
//                                String[] fields = detailsSplitString[i].split("\\*");
//                                if (fields.length >= 7) {
//                                    rec.setAmount1(fields[12].trim());
//                                    extractionRepository.updateStmtRecordWhereAmountIsNull(rec);
//                                }
//                            }
//                        }
//                    }
//                    if (headerString.length > 1) {
//                        String stringReplace = headerString[1].replace(",\\\"", "|").replace("\\", "").replace("\"", "");
//                        String[] detailsSplitString = stringReplace.split("\\|");
//
//                        for (int i = 0; i < detailsSplitString.length; i = i + 1) {
//                            String[] fields = detailsSplitString[i].split("\\*");
//                            if (fields.length >= 7) {
//                                rec.setAmount1(fields[12].trim());
//                                extractionRepository.updateStmtRecordWhereAmountIsNull(rec);
//                            }
//                        }
//                    }
//                }
//                if (splitString.length == 1) {
//                    String[] headerString = splitString[0].replace("\\t\\", "*").replace("\\B\\", "").replace("\\\",\\\",\\\"", "|").split("\\|");
//                    if (headerString.length == 1) {
//                        if (!headerString[0].contains("NO RECORDS RETURNED BY ROUTINE BASED SELECTION")) {
//                            String stringReplace = headerString[0].replace(",\\\"", "|").replace("\\", "").replace("\"", "");
//                            String[] detailsSplitString = stringReplace.split("\\|");
//
//                            for (int i = 0; i < detailsSplitString.length; i = i + 1) {
//                                String[] fields = detailsSplitString[i].split("\\*");
//                                if (fields.length >= 7) {
//                                    rec.setAmount1(fields[12].trim());
//                                    extractionRepository.updateStmtRecordWhereAmountIsNull(rec);
//                                }
//                            }
//                        }
//                    }
//                    if (headerString.length > 1) {
//                        String stringReplace = headerString[1].replace(",\\\"", "|").replace("\\", "").replace("\"", "");
//                        String[] detailsSplitString = stringReplace.split("\\|");
//
//                        for (int i = 0; i < detailsSplitString.length; i = i + 1) {
//                            String[] fields = detailsSplitString[i].split("\\*");
//                            if (fields.length >= 7) {
//                                rec.setAmount1(fields[12].trim());
//                                extractionRepository.updateStmtRecordWhereAmountIsNull(rec);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return "Completed";
//    }
    @Override
    public String updateStmtRecordWithOpeningBalance() {
        List<AccountNumberDump> records = extractionRepository.getAccountNumber();
        if (records != null) {
            for (AccountNumberDump str : records) {

                String ofsRequest = "\"" + "ENQUIRY.SELECT,," + env.getProperty("a24core.T24.inputter.login.credentials").trim()
                        + "," + env.getProperty("a24core.account.statement").trim() + ":EQ=" + str.getAccountNumber()
                        + ",BOOKING.DATE:EQ=20200101"
                        + "\"";

                //Check the environment pointed to
                String response = genericService.ofsResponse(env.getProperty("a24core.accountStatement"), ofsRequest);
                if (response == null) {
                    return messageSource.getMessage("appMessages.invalidMenuPointer", new Object[0], Locale.ENGLISH);
                }
                String[] splitString = response.split("JV.NO::JV.NO,");
                String[] headerString = splitString[1].replace("\\t\\", "*").replace("\\B\\", "").replace("\\\",\\\",\\\"", "|").split("\\|");
                for (int i = 0; i < headerString.length; i = i + 1) {
                    String[] fields = headerString[i].replace("\\\",\\\"", "*").split("\\*");
                    if (fields.length == 7) {
                        if (!fields[4].trim().equals("0.00")) {
                            StmtExtractionOpeningBal rec = new StmtExtractionOpeningBal();
                            rec.setAmount(new BigDecimal(fields[4].trim().replace(",", "")));
                            rec.setNarration("Opening Balance");
                            rec.setFullNarration("Opening Balance brought forward");
                            rec.setBookingDate("2020-01-01");
                            rec.setValueDate("2020-01-01");
                            rec.setInputter("67_COB.USER!");
                            rec.setAuthorizer("67_COB.USER!");
                            rec.setAccountName(fields[2]);
                            rec.setAccountNumber(str.getAccountNumber());
                            rec.setStmtId("S!NGN146");
                            extractionRepository.createStmtRecordWithOpeningBalance(rec);
                            extractionRepository.deleteAccountNumber(str);
                        } else {
                            extractionRepository.deleteAccountNumber(str);
                        }
                    }
                }
            }
        }
        return "Completed";
    }

    @Override
    public String updatePHCNBillerCode() {
        List<XpressPayBillerPackage> phcnBouquet = extractionRepository.allPHCNBouquet();
        if(phcnBouquet != null){
            for(XpressPayBillerPackage p : phcnBouquet){
                String packageName = p.getPackageName();
                if (packageName.equals("EKO Electric Postpaid")) {
                    p.setBillerId(env.getProperty("biller.code.ekoElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("EKO Electric Prepaid")) {
                    p.setBillerId(env.getProperty("biller.code.ekoElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Enugu Electric Postpaid")) {
                    p.setBillerId(env.getProperty("biller.code.enuguElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Enugu Electric Prepaid")) {
                    p.setBillerId(env.getProperty("biller.code.enuguElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Ibadan Electric (POSTPAID)")) {
                    p.setBillerId(env.getProperty("biller.code.ibadanElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Ibadan Electric (PREPAID)")) {
                    p.setBillerId(env.getProperty("biller.code.ibadanElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Ikeja Electric POSTPAID")) {
                    p.setBillerId(env.getProperty("biller.code.ikejaElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Ikeja Electric PREPAID")) {
                    p.setBillerId(env.getProperty("biller.code.ikejaElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Jos Electric Postpaid")) {
                    p.setBillerId(env.getProperty("biller.code.josElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Jos Electric Prepaid")) {
                    p.setBillerId(env.getProperty("biller.code.josElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Kaduna Electric Postpaid")) {
                    p.setBillerId(env.getProperty("biller.code.kadunaElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Kaduna Electric Prepaid")) {
                    p.setBillerId(env.getProperty("biller.code.kadunaElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Kano Postpaid")) {
                    p.setBillerId(env.getProperty("biller.code.kanoElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Kano Prepaid")) {
                    p.setBillerId(env.getProperty("biller.code.kanoElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("PHED2 Postpaid") || packageName.equals("PHED2 Prepaid") ) {
                    p.setBillerId(env.getProperty("biller.code.PHEDElectricity"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Abuja POSTPAID")) {
                    p.setBillerId(env.getProperty("biller.code.abujaElectricity.postpaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
                if (packageName.equals("Abuja Prepaid")) {
                    p.setBillerId(env.getProperty("biller.code.abujaElectricity.prepaid"));
                    extractionRepository.updatePHCNBillerCode(p);
                }
            }
            return "Completed";
        }
        return "null";
    }
}
