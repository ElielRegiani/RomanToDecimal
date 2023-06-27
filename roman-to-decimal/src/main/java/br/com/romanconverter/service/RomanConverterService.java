package br.com.romanconverter.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.romanconverter.Repository.ConversionHistoryRepository;
import br.com.romanconverter.Repository.ConversionRepository;
import br.com.romanconverter.dto.ConversionDTO;
import br.com.romanconverter.models.entities.Conversion;
import br.com.romanconverter.models.entities.ConversionHistory;

@Service
public class RomanConverterService {

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private ConversionHistoryRepository conversionHistoryRepository;

    public void createAndSaveConversion(ConversionDTO conversionDTO) {
        Conversion conversion = new Conversion(conversionDTO.getRomanNumber());
        conversion.setDecimalNumber(convertToDecimal(conversion.getRomanNumber()));
        conversionRepository.saveAndFlush(conversion);
        this.saveConversionByDate(conversion);
    }

    public String convertToDecimal(String romanNumber) {
        int decimalNumber = 0;
        char[] charArray = romanNumber.toUpperCase().toCharArray();
        boolean shouldCalculateLastNumber = true;
        for (int i = 1; i < charArray.length ; i++) {
            int previousNumber = convertRomanCharacterInDecimalNumber(charArray[i - 1]);
            int actualNumber = convertRomanCharacterInDecimalNumber(charArray[i]);
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
        decimalNumber += shouldCalculateLastNumber ? convertRomanCharacterInDecimalNumber(charArray[charArray.length - 1]) : 0;
        return Integer.toString(decimalNumber);
    }

    public int convertRomanCharacterInDecimalNumber(char character) {
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

    public void saveConversionByDate(Conversion conversion) {
        ConversionHistory conversionHistory = getConversionHistoryByDate(new Date());
        if (conversionHistory == null) {
            ConversionHistory newConversionHistory = new ConversionHistory();
            newConversionHistory.addConversion(conversion);
            conversionHistoryRepository.save(newConversionHistory);
        } else {
            conversionHistory.addConversion(conversion);
            conversionHistoryRepository.save(conversionHistory);
        }
    }

    @Transactional
    private ConversionHistory getConversionHistoryByDate(Date date) {
        return conversionHistoryRepository.findAll().stream().filter(it -> it.getDate().equals(date)).findFirst().get();
    }
}
