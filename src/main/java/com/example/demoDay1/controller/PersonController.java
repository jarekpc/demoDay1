package com.example.demoDay1.controller;

import com.example.demoDay1.app.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {

    @GetMapping("/new")
    public String createNewAccount(Model model) {
        model.addAttribute("person", new Person());
        return "account";
    }

    @PostMapping("/new")
    public String createNewAccount(@ModelAttribute Person person) {
        if (person.getUserRole().equals("AUTHOR"))
            return "author";
        else
            return "customer";
    }

}
