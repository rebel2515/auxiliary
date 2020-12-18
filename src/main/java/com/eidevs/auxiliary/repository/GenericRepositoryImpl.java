/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.repository;

import com.eidevs.auxiliary.model.SystemParameters;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Emmanuel W. Israel israelewisdom@gmail.com
 */
@Repository
public class GenericRepositoryImpl implements GenericRepository {

    @PersistenceContext(unitName = "corePersistenceUnit")
    EntityManager em;

    @Override
    public SystemParameters getSystemParameterUsingName(String paramName) {
        TypedQuery<SystemParameters> query = em.createQuery("SELECT p FROM SystemParameters p WHERE p.paramName =:paramName", SystemParameters.class)
                .setParameter("paramName", paramName);
        List<SystemParameters> parameters = query.getResultList();
        if (parameters.isEmpty()) {
            return null;
        }
        return parameters.get(0);
    }

    @Override
    public String getParameterValue(String paramName) {
        TypedQuery<String> query = em.createQuery("SELECT p.paramValue FROM SystemParameters p WHERE p.paramName = :paramName", String.class)
                .setParameter("paramName", paramName);
        List<String> selectedParam = query.getResultList();
        if (selectedParam.isEmpty()) {
            return null;
        }
        return selectedParam.get(0);
    }
    
    @Override
    public String getBranchNameUsingCode(String branchCode) {
        TypedQuery<String> query = em.createQuery("SELECT b.branchName FROM Branch b WHERE b.branchCode = :branchCode", String.class)
                .setParameter("branchCode", branchCode);
        List<String> branches = query.getResultList();
        if (branches.isEmpty()) {
            return null;
        }
        return branches.get(0);
    }
}
