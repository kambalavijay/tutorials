package com.example.tutorial.protos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainReader {

    static void print(AddressBook addressBook) {
        for (Person person: addressBook.getPeopleList()) {
            System.out.println("Person ID: " + person.getId());
            System.out.println("  Name: " + person.getName());
            if (person.hasEmail()) {
                System.out.println("  E-mail address: " + person.getEmail());
            }

            for (Person.PhoneNumber phoneNumber : person.getPhonesList()) {
                switch (phoneNumber.getType()) {
                    case PHONE_TYPE_MOBILE:
                        System.out.print("  Mobile phone #: ");
                        break;
                    case PHONE_TYPE_HOME:
                        System.out.print("  Home phone #: ");
                        break;
                    case PHONE_TYPE_WORK:
                        System.out.print("  Work phone #: ");
                        break;
                }
                System.out.println(phoneNumber.getNumber());
            }
        }
    }
    public static void main(String[] args) throws IOException {

        // Read the existing address book.
        AddressBook addressBook = AddressBook.parseFrom(new FileInputStream("file1"));

        print(addressBook);
    }
}
