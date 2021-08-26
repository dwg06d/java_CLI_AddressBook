package com.tts.addresser;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Addresser {

    private final ArrayList<Entry> myList;

    public enum SearchType {
        FIRSTNAME,
        LASTNAME,
        PHONE,
        EMAIL
    }

    public static Addresser createEmptyAddresser() {
        return new Addresser();
    }

    private Addresser() {
        myList = new ArrayList<>();
    }

    public void addEntry(Entry newEntry) throws InputMismatchException {
        ArrayList<Entry> searchResults = searchEntries(newEntry.getEmailAddress(), SearchType.EMAIL);
        if (searchResults.isEmpty()) {
            myList.add(newEntry);
        } else {
            throw new InputMismatchException("Email address already in book!");
        }
    }

    public void removeEntry(String email) throws NullPointerException {
        Entry searchEntry;
        try {
            searchEntry = searchEntries(email, SearchType.EMAIL).get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NullPointerException("No entry found with the specified email address.");
        }
        if (searchEntry != null) {
            myList.remove(searchEntry);
        }
    }

    public ArrayList<Entry> searchEntries(String searchQuery) {
        ArrayList<Entry> searchList = new ArrayList<>();
        for (Entry entry : myList) {
            if ((entry.getFirstName().contains(searchQuery) ||
                    entry.getLastName().contains(searchQuery) ||
                    entry.getPhoneNumber().contains(searchQuery) ||
                    entry.getEmailAddress().contains(searchQuery)) &&
                    !(searchList.contains(entry))) {
                searchList.add(entry);
            }
        }
        return searchList;
    }

    public ArrayList<Entry> searchEntries(String searchQuery, SearchType searchType) {
        ArrayList<Entry> searchList = new ArrayList<>();
        for (Entry entry : myList) {
            String searchResult = "";
            switch (searchType) {
                case FIRSTNAME -> searchResult = entry.getFirstName();
                case LASTNAME -> searchResult = entry.getLastName();
                case PHONE -> searchResult = entry.getPhoneNumber();
                case EMAIL -> searchResult = entry.getEmailAddress();
            }

            if ((searchResult.contains(searchQuery)) &&
                    !(searchList.contains(entry))) {
                searchList.add(entry);
            }
        }
        return searchList;

    }

    public void printAddressBook() {
        if (myList.isEmpty()) {
            System.out.println("No entries in this address book!");
        } else {
            System.out.println("The contents of this address book:");
            for (Entry entry : myList) {
                System.out.println(entry.toString());
            }
        }
    }

    public void deleteAddressBook() {
        myList.clear();
    }
}