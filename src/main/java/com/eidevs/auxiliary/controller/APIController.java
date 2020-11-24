/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.eidevs.auxiliary.controller;

import com.eidevs.auxiliary.service.ExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author eisrael
 */
@RestController
@RequestMapping("/api/service")
public class APIController {
    @Autowired
    ExtractionService extractionService;
    
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
    
    @GetMapping("/stmt/update")
    public String stmtUpdate() {
        String result = extractionService.updateStmtRecordWithClosingBalance();
        return result;
    }
}
