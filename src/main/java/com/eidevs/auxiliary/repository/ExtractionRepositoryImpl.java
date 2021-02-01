/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.repository;

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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eisrael
 */
@Repository
public class ExtractionRepositoryImpl implements ExtractionRepository {

    @PersistenceContext(unitName = "corePersistenceUnit")
    EntityManager em;

    @Override
    public T24Accounts getBranchCodeWithAccountNumber(String accountNumber) {
        TypedQuery<T24Accounts> query = em.createQuery("SELECT c FROM T24Accounts c WHERE c.accountNumber = :accountNumber OR c.nubanAccountNumber = :accountNumber", T24Accounts.class)
                .setParameter("accountNumber", accountNumber);
        List<T24Accounts> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record.get(0);
    }

    @Override
    public List<DisableUsers> getListOfUsersToDisable() {
        TypedQuery<DisableUsers> query = em.createQuery("SELECT c FROM DisableUsers c", DisableUsers.class);
        List<DisableUsers> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record;
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void updateUserStatus(Users user) {
        em.merge(user);
        em.flush();
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void deleteDisableUser(DisableUsers disableUsers) {
        em.remove(em.contains(disableUsers) ? disableUsers : em.merge(disableUsers));
        em.flush();
    }

    @Override
    public Users getUserWithUsername(String username) {
        TypedQuery<Users> query = em.createQuery("SELECT c FROM Users c WHERE c.username = :username", Users.class)
                .setParameter("username", username);
        List<Users> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record.get(0);
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void createBillerPackage(XpressPayBillerPackage biller) {
        em.persist(biller);
        em.flush();
    }

    @Override
    public List<FTRecord> getAllAISTransaction() {
        TypedQuery<FTRecord> query = em.createQuery("SELECT c FROM FTRecord c", FTRecord.class);
        List<FTRecord> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record;
    }

    @Override
    public TellerHistory getTRansactionDoneToday(String ftId) {
        TypedQuery<TellerHistory> query = em.createQuery("SELECT c FROM TellerHistory c WHERE c.transId = :ftID", TellerHistory.class)
                .setParameter("ftID", ftId);
        List<TellerHistory> record = query.getResultList();
        if (record.isEmpty()) {
            return null;
        }
        return record.get(0);
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void createMissingFTRecord(FTMissingToday rec) {
        em.persist(rec);
        em.flush();
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void updateFT(FTRecord ft) {
        em.merge(ft);
        em.flush();
    }

    @Override
    public List<String> getAllRecordWhereAmountIsNull() {
        TypedQuery<String> query = em.createQuery("SELECT s.accountNumber FROM StmtExtraction AS s WHERE s.accountNumber = 'NGN1460000010009'", String.class);
        List<String> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records;
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void updateStmtRecordWhereAmountIsNull(StmtExtraction stmt) {
        em.merge(stmt);
        em.flush();
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void createStmtRecordWithOpeningBalance(StmtExtractionOpeningBal stmt) {
        em.persist(stmt);
        em.flush();
    }

    @Override
    public List<AccountNumberDump> getAccountNumber() {
        TypedQuery<AccountNumberDump> query = em.createQuery("SELECT s FROM AccountNumberDump s WHERE s.accountNumber LIKE 'NGN%'", AccountNumberDump.class);
        List<AccountNumberDump> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records;
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void deleteAccountNumber(AccountNumberDump acNO) {
        em.remove(em.contains(acNO) ? acNO : em.merge(acNO));
        em.flush();
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public void updatePHCNBillerCode(XpressPayBillerPackage biller) {
        em.merge(biller);
        em.flush();
    }

    @Override
    public List<XpressPayBillerPackage> allPHCNBouquet() {
        TypedQuery<XpressPayBillerPackage> query = em.createQuery("SELECT s FROM XpressPayBillerPackage s WHERE s.biller = 'PHCN'", XpressPayBillerPackage.class);
        List<XpressPayBillerPackage> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records;
    }

    @Override
    public Teller getTellerRecord(String branchCode, String tellerAccount) {
        TypedQuery<Teller> query = em.createQuery("SELECT s FROM Teller s WHERE s.branch =:branchCode AND s.tellerAccount =:tellerAccount", Teller.class)
                .setParameter("branchCode", branchCode)
                .setParameter("tellerAccount", tellerAccount);
        List<Teller> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public TellerTemp createTempRecord(TellerTemp temp) {
        em.persist(temp);
        em.flush();
        return temp;
    }

    @Override
    public String getTellerName(String tellerId, String branchCode) {
        TypedQuery<String> query = em.createQuery("SELECT s.tellerName FROM Teller s WHERE s.branch =:branchCode AND s.tellerId =:tellerId", String.class)
                .setParameter("branchCode", branchCode)
                .setParameter("tellerId", tellerId);
        List<String> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    public String getCorrespondingBankName(String accountNumber) {
        TypedQuery<String> query = em.createQuery("SELECT s.bankName FROM CorrespondingBank s WHERE s.accountNumber =:accountNumber", String.class)
                .setParameter("accountNumber", accountNumber);
        List<String> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    public Teller getTellerWithBranchCode(String branchCode) {
        TypedQuery<Teller> query = em.createQuery("SELECT s FROM Teller s WHERE s.branch =:branchCode AND s.username != 'NULL' AND s.tellerName != 'Vault'", Teller.class)
                .setParameter("branchCode", branchCode)
                .setMaxResults(1);
        List<Teller> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    public String getAccountName(String accountNumber) {
        TypedQuery<String> query = em.createQuery("SELECT s.accountName FROM T24Accounts s WHERE s.accountNumber =:accountNumber", String.class)
                .setParameter("accountNumber", accountNumber);
        List<String> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    public TellerTemp getRecordWithTransId(String transId) {
        TypedQuery<TellerTemp> query = em.createQuery("SELECT s FROM TellerTemp s WHERE s.transId =:transId", TellerTemp.class)
                .setParameter("transId", transId);
        List<TellerTemp> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public TellerHistory createTellerHistory(TellerHistory tellerHistory) {
        em.persist(tellerHistory);
        em.flush();
        return tellerHistory;
    }

    @Override
    public TellerHistory getRecordFromHistoryWithTransId(String transId) {
        TypedQuery<TellerHistory> query = em.createQuery("SELECT s FROM TellerHistory s WHERE s.transId =:transId", TellerHistory.class)
                .setParameter("transId", transId);
        List<TellerHistory> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records.get(0);
    }

    @Override
    public List<TellerTemp> getAllPendingTransactions() {
        TypedQuery<TellerTemp> query = em.createQuery("SELECT s FROM TellerTemp s WHERE s.transId IS NOT NULL AND s.status IS NOT NULL", TellerTemp.class);
        List<TellerTemp> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records;
    }

    @Override
    @Transactional(transactionManager = "coreTransactionManager")
    public TellerTemp dropTellerTemp(TellerTemp tellerTemp) {
        em.remove(em.contains(tellerTemp) ? tellerTemp : em.merge(tellerTemp));
        em.flush();
        return tellerTemp;
    }
}
