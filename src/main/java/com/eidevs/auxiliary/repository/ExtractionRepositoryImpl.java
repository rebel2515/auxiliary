/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.repository;

import com.eidevs.auxiliary.model.DisableUsers;
import com.eidevs.auxiliary.model.FTMissingToday;
import com.eidevs.auxiliary.model.FTRecord;
import com.eidevs.auxiliary.model.StmtExtraction;
import com.eidevs.auxiliary.model.T24Accounts;
import com.eidevs.auxiliary.model.TellerHistory;
import com.eidevs.auxiliary.model.Users;
import com.eidevs.auxiliary.model.XpressPayBillerPackage;
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
    @Transactional
    public void updateUserStatus(Users user) {
        em.merge(user);
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
    @Transactional
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
    @Transactional
    public void createMissingFTRecord(FTMissingToday rec) {
        em.persist(rec);
        em.flush();
    }

    @Override
    @Transactional
    public void updateFT(FTRecord ft) {
        em.merge(ft);
        em.flush();
    }

    @Override
    public List<StmtExtraction> getAllRecordWhereAmountIsNull() {
        TypedQuery<StmtExtraction> query = em.createQuery("SELECT s FROM StmtExtraction AS s WHERE s.amount IS NULL AND s.amount1 IS NULL", StmtExtraction.class);
        List<StmtExtraction> records = query.getResultList();
        if (records.isEmpty()) {
            return null;
        }
        return records;
    }

    @Override
    @Transactional
    public void updateStmtRecordWhereAmountIsNull(StmtExtraction stmt) {
        em.merge(stmt);
        em.flush();
    }
}
