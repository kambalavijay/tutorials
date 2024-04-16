package com.example.tutorial.protos;

import com.google.protobuf.util.JsonFormat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainWriter {

    static Person PromptForAddress(BufferedReader stdin, PrintStream stdout) throws IOException {
        Person.Builder person = Person.newBuilder();

        stdout.print("Enter person ID: ");
        person.setId(Integer.valueOf(stdin.readLine()));

        stdout.print("Enter name: ");
        person.setName(stdin.readLine());

        stdout.print("Enter email address (blank for none): ");
        String email = stdin.readLine();
        if (email.length() > 0) {
            person.setEmail(email);
        }

        while (true) {
            stdout.print("Enter a phone number (or leave blank to finish): ");
            String number = stdin.readLine();
            if (number.length() == 0) {
                break;
            }

            Person.PhoneNumber.Builder phoneNumber =
                    Person.PhoneNumber.newBuilder().setNumber(number);

            stdout.print("Is this a mobile, home, or work phone? ");
            String type = stdin.readLine();
            if (type.equals("mobile")) {
                phoneNumber.setType(Person.PhoneType.PHONE_TYPE_MOBILE);
            } else if (type.equals("home")) {
                phoneNumber.setType(Person.PhoneType.PHONE_TYPE_HOME);
            } else if (type.equals("work")) {
                phoneNumber.setType(Person.PhoneType.PHONE_TYPE_WORK);
            } else {
                stdout.println("Unknown phone type.  Using default.");
            }

            person.addPhones(phoneNumber);
        }

        return person.build();
    }
    public static void main(String[] args) throws IOException {

        /*AddressBook.Builder addressBook = AddressBook.newBuilder();

        // Read the existing address book.
        try {
            addressBook.mergeFrom(new FileInputStream("file1"));
        } catch (IOException e) {
            //System.out.println(args[0] + ": File not found.  Creating a new file.");
        }*/

        // Add an address.
        //addressBook.addPeople(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));
        Person p = Person.newBuilder()
                        .addAllPhones(List.of(Person.PhoneNumber.newBuilder()
                                        .setType(Person.PhoneType.PHONE_TYPE_HOME)
                                        .setNumber("1232342342")
                                .build()))
                        .setEmail("test@gmail.com")
                        .setId(1)
                        .setName("Vijay")
                .build();

        // Write the new address book back to disk.
       // FileOutputStream output = new FileOutputStream("file1");
       // addressBook.build().writeTo(output);
        //output.close();
        System.out.println(JsonFormat.printer().print(p));
        System.out.println(Arrays.toString(p.toByteArray()));

        StringBuilder hexString = new StringBuilder();
        List<String> hexas = new ArrayList<>();
        for (byte b : p.toByteArray()) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexas.add("0"+hex);
                continue;
            }
            hexas.add(hex);
        }
        System.out.println(hexas);
        System.out.println(hexas.size());
    }

    public static String protobufToString(MessageProto.Test1 message)  {
        // Serialize the protobuf message to bytes
        byte[] bytes = message.toByteArray();

        // Convert bytes to a UTF-8 string
        String utf8String = new String(bytes, StandardCharsets.UTF_8);

        return utf8String;
    }

    public static void main1(String[] args) throws IOException {

        MessageProto.Test1 test1 = MessageProto.Test1.newBuilder()
                .setA(150)
                .build();

        // Write the new address book back to disk.
        /*FileOutputStream output = new FileOutputStream("file2");
        test1.writeTo(output);
        output.close();*/

        //String utf8String = protobufToString(test1);
        System.out.println(Arrays.toString(test1.toByteArray()));
        System.out.println(JsonFormat.printer().print(test1));
    }
}
