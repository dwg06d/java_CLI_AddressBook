package com.tts.addresser;

public class Entry {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;


    private Entry(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public static Entry createBlankEntry() {
        return new Entry("", "", "", "");
    }

    public static Entry createEntry(String firstName, String lastName, String phoneNumber, String emailAddress) {
        return new Entry(firstName, lastName, phoneNumber, emailAddress);
    }

    @Override
    public String toString() {
        return "First Name: " + this.firstName + "\n" +
                "Last Name: " + this.lastName + "\n" +
                "Phone Number: " + this.phoneNumber + "\n" +
                "Email Address: " + this.emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


}
