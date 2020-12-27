package com.example.demoDay1.controller;

import com.example.demoDay1.Calculator;
import com.example.demoDay1.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculator")
    public String greeting(@RequestParam(name = "x", required = false, defaultValue = "1") int x, @RequestParam(name = "x", required = false, defaultValue = "2") int y, Model model) {
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        model.addAttribute("calculator", new Calculator());
        System.out.println("Przeslales " + x + " oraz " + y);
        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculatorSubmit(@ModelAttribute Calculator calculator) {
        //operacja
        int result = calculatorService.add(calculator.getX(), calculator.getY());
        calculator.setResult(result);
        System.out.println(calculator.getX() + " " + calculator.getResult());
        return "calculator_result";
    }
}
