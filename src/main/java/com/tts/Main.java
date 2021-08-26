package com.tts;

import com.tts.addresser.Addresser;
import com.tts.addresser.Entry;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Addresser myAddresser = Addresser.createEmptyAddresser();
    private static final Scanner kb = new Scanner(System.in);
    private static boolean keepGoing = true;

    public static void main(String[] args) {

        System.out.println("Hello, and welcome to the Address program!");

        do {
            System.out.println("1) Add an entry");
            System.out.println("2) Remove an entry");
            System.out.println("3) Search for a specific entry");
            System.out.println("4) Print address book");
            System.out.println("5) Empty address book");
            System.out.println("6) Quit");
            System.out.println("Please enter the number of an operation to perform:");
            String response = kb.nextLine();

            switch (response) {
                case "1" -> addEntry();
                case "2" -> removeEntry();
                case "3" -> searchEntries();
                case "4" -> printAddressBook();
                case "5" -> emptyAddressBook();
                case "6" -> quit();
                default -> {
                    System.out.println("Invalid option, please choose again!");
                    pressEnter();
                }
            }
        } while (keepGoing);
    }

    private static void addEntry() {
        System.out.println("Let's add an entry!");
        System.out.println("First name (may be empty): ");
        String entryFirstName = kb.nextLine();
        System.out.println("Last name (may be empty): ");
        String entryLastName = kb.nextLine();
        System.out.println("Phone number (may be empty): ");
        String entryPhone = kb.nextLine();
        System.out.println("Email address (may not be empty, and must be unique): ");
        String entryEmail = kb.nextLine();

        Entry newEntry = Entry.createEntry(entryFirstName, entryLastName, entryPhone, entryEmail);

        try {
            myAddresser.addEntry(newEntry);
            System.out.println("Added the following entry:");
            System.out.println(newEntry.toString());
        } catch (InputMismatchException e) {
            System.out.println("Entry not added: " + e.getMessage());
        }
        pressEnter();
    }

    private static void removeEntry() {
        System.out.println("Enter the email address of the entry you would like to remove:");
        String removeChoice = kb.nextLine();
        try {
            myAddresser.removeEntry(removeChoice);
        } catch (NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        pressEnter();
    }

    private static void searchEntries() {
        System.out.println("Searching entries!");
        ArrayList<Entry> searchResults = new ArrayList<>();
        boolean searchChoice;
        do {
            searchChoice = true;
            System.out.println("""
                    1) Search all fields
                    2) First Name
                    3) Last Name
                    4) Phone Number
                    5) Email Address
                    6) Cancel
                    """);
            System.out.println("Enter the number of the option you would like to search by:");
            String response = kb.nextLine();

            switch (response) {
                case "1" -> {
                    System.out.println("Enter a term to search all fields:");
                    response = kb.nextLine();
                    searchResults = myAddresser.searchEntries(response);
                }
                case "2" -> {
                    System.out.println("Enter a First Name to search by:");
                    response = kb.nextLine();
                    searchResults = myAddresser.searchEntries(response, Addresser.SearchType.FIRSTNAME);
                }
                case "3" -> {
                    System.out.println("Enter a Last Name to search by:");
                    response = kb.nextLine();
                    searchResults = myAddresser.searchEntries(response, Addresser.SearchType.LASTNAME);
                }
                case "4" -> {
                    System.out.println("Enter a Phone Number to search by:");
                    response = kb.nextLine();
                    searchResults = myAddresser.searchEntries(response, Addresser.SearchType.PHONE);
                }
                case "5" -> {
                    System.out.println("Enter an Email Address to search by:");
                    response = kb.nextLine();
                    searchResults = myAddresser.searchEntries(response, Addresser.SearchType.EMAIL);
                }
                case "6" -> System.out.println("Cancelling search!");
                default -> {
                    System.out.println("Unrecognized input, try again!");
                    searchChoice = false;
                }
            }
        } while (!searchChoice);

        if (searchResults.isEmpty()) {
            System.out.println("No matching entries in this address book!");
        } else {
            System.out.println("The matching contents of this address book:");
            for (Entry entry : searchResults) {
                System.out.println(entry.toString());
            }
        }
        pressEnter();
    }

    private static void printAddressBook() {
        System.out.println("Printing address book!");
        myAddresser.printAddressBook();
        pressEnter();
    }

    private static void emptyAddressBook() {
        System.out.println("Emptying address book!");
        myAddresser.deleteAddressBook();
        System.out.println("Done!");
        pressEnter();
    }

    private static void quit() {
        System.out.println("Quitting!");
        keepGoing = false;
    }

    private static void pressEnter() {
        System.out.println("Press enter to continue!");
        kb.nextLine();
    }


}
