package br.com.romanconverter.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONVERSION")
public class Conversion {

    private Long id;
    private String romanNumber;
    private String decimalNumber;

    public Conversion(String romanNumber) {
        this.romanNumber = validateRomanNumber(romanNumber);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ROMAN_NUMBER")
    public String getRomanNumber() {
        return romanNumber;
    }

    public void setRomanNumber(String romanNumber) {
        this.romanNumber = romanNumber;
    }

    @Column(name = "DECIMAL_NUMBER")
    public String getDecimalNumber() {
        return this.decimalNumber;
    }

    public void setDecimalNumber(String decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public String validateRomanNumber(String numberInput) {
        String romanNumber = numberInput.toLowerCase().trim();
        if (hasNumberOrInvalidCharacter(romanNumber)) {
            throw new RuntimeException("Has number or invalid character!");
        }
        if (invalidRomanFormat(romanNumber)) {
            throw new RuntimeException("Invalid roman number format!");
        }
        return romanNumber;
    }

    private boolean invalidRomanFormat(String romanNumber) {
        return romanNumber.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
    }

    public boolean hasNumberOrInvalidCharacter(String romanNumber) {
        return !romanNumber.matches("[a-zA-Z]+");
    }
}
