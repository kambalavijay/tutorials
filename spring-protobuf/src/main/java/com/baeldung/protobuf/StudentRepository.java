package com.baeldung.protobuf;


import java.util.Map;

public class StudentRepository {

    private final Map<Integer, StudentProtos.Student> students;

    public StudentRepository(Map<Integer, StudentProtos.Student> students) {
        this.students = students;
    }

    public StudentProtos.Student getStudent(int id) {
        return students.get(id);
    }
}
