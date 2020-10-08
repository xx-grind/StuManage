package com.rs.stuManage.service;

import com.rs.stuManage.entity.Student;

import java.util.List;

public interface StudentService {
    int addStu(Student student);

    List<Student> getAll();
    Student getStu(String studentId);

    int changeStu(Student student);

    int deleteStu(String studentId);
}
