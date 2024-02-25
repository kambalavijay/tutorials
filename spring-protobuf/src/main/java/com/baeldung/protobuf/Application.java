package com.baeldung.protobuf;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.baeldung.protobuf.StudentProtos.Student;
import com.baeldung.protobuf.StudentProtos.Student.PhoneNumber;
import com.baeldung.protobuf.StudentProtos.Student.PhoneType;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(Arrays.asList(hmc));
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    public StudentRepository createStudentRepo() {

        Map<Integer, Student> students = new HashMap<>();

        Student student = createStudent(1, "Fn1", "Ln1", "testfn1@test.com", List.of(createPhone("23234234", PhoneType.MOBILE)));
        Student student1 = createStudent(2, "Fn2", "Ln2", "testfn2@test.com", List.of(createPhone("343423423", PhoneType.MOBILE)));

        students.put(student.getId(), student);
        students.put(student1.getId(), student1);

        return new StudentRepository(students);
    }

    private Student createStudent(int id, String firstName, String lastName, String email, List<Student.PhoneNumber> phones) {
        return Student.newBuilder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .addAllPhone(phones)
                .build();
    }

    private PhoneNumber createPhone(String number, PhoneType type) {
        return PhoneNumber.newBuilder().setNumber(number).setType(type).build();
    }
}