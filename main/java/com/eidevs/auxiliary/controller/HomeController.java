/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.eidevs.auxiliary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author bokon
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
