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

    public Conversion(Long id, String romanNumber, String decimalNumber) {
        this.id = id;
        this.romanNumber = romanNumber;
        this.decimalNumber = decimalNumber;
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
}
