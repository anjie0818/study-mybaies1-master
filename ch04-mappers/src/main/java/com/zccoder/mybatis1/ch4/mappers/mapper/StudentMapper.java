package com.zccoder.mybatis1.ch4.mappers.mapper;

import com.zccoder.mybatis1.ch4.mappers.po.Student;

import java.util.List;

public interface StudentMapper {
    //新增学生
    int addStudent(Student student);
    //查询学生
    Student selectStudentById(Integer id);

    List<Student> findAllStudent();
}
