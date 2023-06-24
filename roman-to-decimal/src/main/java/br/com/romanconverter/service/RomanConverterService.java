package br.com.romanconverter.service;

import org.springframework.stereotype.Service;

@Service
public class RomanConverterService {

    public String convertToDecimal(String romanNumber) {
        int decimalNumber = 0;
        char[] charArray = romanNumber.toUpperCase().toCharArray();
        boolean shouldCalculateLastNumber = true;
        for (int i = 1; i < charArray.length ; i++) {
            int previousNumber = convertRomanCharacterInDecimal(charArray[i - 1]);
            int actualNumber = convertRomanCharacterInDecimal(charArray[i]);
            if (previousNumber < actualNumber) {
                decimalNumber += (actualNumber - previousNumber);
                i++;
                if (i >= charArray.length) {
                    shouldCalculateLastNumber = false;
                }
            } else {
                decimalNumber += previousNumber;
            }
        }
        decimalNumber += shouldCalculateLastNumber ? convertRomanCharacterInDecimal(charArray[charArray.length - 1]) : 0;
        return Integer.toString(decimalNumber);
    }

    public int convertRomanCharacterInDecimal(char character) {
        int decimalNumber = 0;
        switch (character) {
            case 'I':
                decimalNumber = 1;
                break;
            case 'V':
                decimalNumber = 5;
                break;
            case 'X':
                decimalNumber = 10;
                break;
            case 'L':
                decimalNumber = 50;
                break;
            case 'C':
                decimalNumber = 100;
                break;
            case 'D':
                decimalNumber = 500;
                break;
            case 'M':
                decimalNumber = 1000;
                break;
        }
        return decimalNumber;
    }
}
