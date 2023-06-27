package br.com.romanconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.romanconverter.dto.ConversionDTO;
import br.com.romanconverter.service.RomanConverterService;

@Controller
@RequestMapping("converter")
public class RomanConverterController {

    @Autowired
    private RomanConverterService romanConverterService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String converter() {
        return "/converter";
    }

    @PostMapping("convert")
    public String convert(ConversionDTO conversionDTO) {
        romanConverterService.createAndSaveConversion(conversionDTO);
        return "converter";
    }
}
