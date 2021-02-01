/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.eidevs.auxiliary.repository;

import com.eidevs.auxiliary.model.AccountNumberDump;
import com.eidevs.auxiliary.model.DisableUsers;
import com.eidevs.auxiliary.model.FTMissingToday;
import com.eidevs.auxiliary.model.FTRecord;
import com.eidevs.auxiliary.model.StmtExtraction;
import com.eidevs.auxiliary.model.StmtExtractionOpeningBal;
import com.eidevs.auxiliary.model.Teller;
import com.eidevs.auxiliary.model.TellerHistory;
import com.eidevs.auxiliary.model.TellerTemp;
import com.eidevs.auxiliary.model.Users;
import com.eidevs.auxiliary.model.XpressPayBillerPackage;
import com.eidevs.auxiliary.model.customerAccount.T24Accounts;
import java.util.List;

/**
 *
 * @author eisrael
 */
public interface ExtractionRepository {
    
    T24Accounts getBranchCodeWithAccountNumber(String accountNumber);
    
    List<DisableUsers> getListOfUsersToDisable();
    
    void updateUserStatus(Users user);
    
    void deleteDisableUser(DisableUsers disableUsers);
    
    Users getUserWithUsername(String username);
    
    void createBillerPackage(XpressPayBillerPackage biller);
    
    List<FTRecord> getAllAISTransaction();
    
    TellerHistory getTRansactionDoneToday(String ftId);
    
    void createMissingFTRecord(FTMissingToday rec);
    
    void updateFT(FTRecord ft);
    
    List<String> getAllRecordWhereAmountIsNull();
    
    void updateStmtRecordWhereAmountIsNull(StmtExtraction stmt);
    
    void createStmtRecordWithOpeningBalance(StmtExtractionOpeningBal stmt);
    
    List<AccountNumberDump> getAccountNumber();
    
    void deleteAccountNumber(AccountNumberDump acNO);
    
    void updatePHCNBillerCode(XpressPayBillerPackage biller);
    
    List<XpressPayBillerPackage> allPHCNBouquet();
    
    Teller getTellerRecord(String branchCode, String tellerAccount);
    
    TellerTemp createTempRecord(TellerTemp record);
    
    String getTellerName(String tellerId, String branchCode);
    
    String getCorrespondingBankName(String accountNumber);
    
    Teller getTellerWithBranchCode(String branchCode);
    
    String getAccountName(String accountNumber);
    
    TellerTemp getRecordWithTransId(String transId);
    
    TellerHistory createTellerHistory(TellerHistory tellerHistory);
    
    TellerHistory getRecordFromHistoryWithTransId(String transId);
    
    List<TellerTemp> getAllPendingTransactions();
    
    TellerTemp dropTellerTemp(TellerTemp tellerTemp);
}
