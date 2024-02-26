package com.example.tutorial.protos;

import com.google.protobuf.MessageLite;
import com.google.protobuf.util.JsonFormat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForwardBackwardCompatibility {
    public static void main(String[] args) throws IOException {

        Person person = Person.newBuilder()
                        .addAllPhones(List.of(Person.PhoneNumber.newBuilder()
                                        .setType(Person.PhoneType.PHONE_TYPE_HOME)
                                        .setNumber("1232342342")
                                .build()))
                        .setEmail("test@gmail.com")
                        .setId(1)
                        .setName("Vijay")
                .build();

        // Write the new address book back to disk.
        FileOutputStream output = new FileOutputStream("file1");
        person.writeTo(output);
        output.close();

        try {
            Person.Builder personBuilder = Person.newBuilder();
            personBuilder.mergeFrom(new FileInputStream("file1"));
            System.out.println(JsonFormat.printer().print(personBuilder.build()).getBytes().length);
            System.out.println(person.toByteArray().length);
        } catch (IOException e) {
            //System.out.println(args[0] + ": File not found.  Creating a new file.");
        }
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
