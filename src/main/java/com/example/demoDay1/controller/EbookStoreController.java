package com.example.demoDay1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EbookStoreController {

    @Value("${spring.application.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @RequestMapping("/")
    public String index(final Model model){
        model.addAttribute("name",name);
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
