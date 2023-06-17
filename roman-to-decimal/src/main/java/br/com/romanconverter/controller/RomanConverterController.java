package br.com.romanconverter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RomanConverterController {

    @GetMapping("/converter")
    @ResponseStatus(HttpStatus.OK)
    public String converter() {
        return "/converter";
    }
}
