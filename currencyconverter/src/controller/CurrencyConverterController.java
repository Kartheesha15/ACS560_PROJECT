package com.example.currencyconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.currencyconverter.model.CurrencyConversion;
import com.example.currencyconverter.service.CurrencyConverterService;

@Controller
@RequestMapping("/api/v1")
public class CurrencyConverterController {
   @Autowired
    private CurrencyConverterService converterService;

    @GetMapping("/converter")
    public String showConverterForm(Model model) {
        model.addAttribute("conversion", new CurrencyConversion());
        return "converter";
    }

    @PostMapping("/convert")
    public String convertCurrency(CurrencyConversion conversion, Model model) {
        double result = converterService.convert(conversion.getAmount(), conversion.getFromCurrency(), conversion.getToCurrency());
        conversion.setConvertedAmount(result);
        model.addAttribute("conversion", conversion);
        return "result";
    } 
}
