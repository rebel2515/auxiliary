/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.eidevs.auxiliary.repository;

import com.eidevs.auxiliary.model.DisableUsers;
import com.eidevs.auxiliary.model.FTMissingToday;
import com.eidevs.auxiliary.model.FTRecord;
import com.eidevs.auxiliary.model.StmtExtraction;
import  com.eidevs.auxiliary.model.T24Accounts;
import com.eidevs.auxiliary.model.TellerHistory;
import com.eidevs.auxiliary.model.Users;
import com.eidevs.auxiliary.model.XpressPayBillerPackage;
import java.util.List;

/**
 *
 * @author eisrael
 */
public interface ExtractionRepository {
    
    T24Accounts getBranchCodeWithAccountNumber(String accountNumber);
    
    List<DisableUsers> getListOfUsersToDisable();
    
    void updateUserStatus(Users user);
    
    Users getUserWithUsername(String username);
    
    void createBillerPackage(XpressPayBillerPackage biller);
    
    List<FTRecord> getAllAISTransaction();
    
    TellerHistory getTRansactionDoneToday(String ftId);
    
    void createMissingFTRecord(FTMissingToday rec);
    
    void updateFT(FTRecord ft);
    
    List<StmtExtraction> getAllRecordWhereAmountIsNull();
    
    void updateStmtRecordWhereAmountIsNull(StmtExtraction stmt);
}
