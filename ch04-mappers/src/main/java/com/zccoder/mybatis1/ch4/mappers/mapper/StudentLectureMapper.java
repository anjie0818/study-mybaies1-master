package com.zccoder.mybatis1.ch4.mappers.mapper;

import com.zccoder.mybatis1.ch4.mappers.po.StudentLecture;

public interface StudentLectureMapper {
    StudentLecture selectStudentLectureByStudentId(Integer studentId);
}
