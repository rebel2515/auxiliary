/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.eidevs.auxiliary.repository;

import  com.eidevs.auxiliary.model.SystemParameters;

/**
 *
 * @author Emmanuel W. Israel israelewisdom@gmail.com
 */
public interface GenericRepository {

    SystemParameters getSystemParameterUsingName(String paramName);

    String getParameterValue(String paramName);
}
