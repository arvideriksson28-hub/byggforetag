package com.example.byggforetag.embeddable;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Address {
    @NotBlank(message = "Gata får inte vara tom")
    private String street;
    @NotBlank(message = "Stad får inte vara tom")
    private String city;
    @NotBlank(message = "Postnummer får inte vara tom")
    private String zipCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
