package com.biteplate.biteplate_api.reservation.domain.valueobject;


public class CustomerInfo {

    private String fullName;
    private String phoneNumber;
    private String email;

    public CustomerInfo(
            String fullName,
            String phoneNumber,
            String email
    ) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

}