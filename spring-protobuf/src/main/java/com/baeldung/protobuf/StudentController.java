package com.baeldung.protobuf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.protobuf.StudentProtos.Student;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/students/{id}")
    Student student(@PathVariable Integer id) {
        return studentRepository.getStudent(id);
    }
}