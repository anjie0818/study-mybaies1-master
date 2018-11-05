package com.zccoder.mybatis1.ch4.mappers.mapper;

import com.zccoder.mybatis1.ch4.mappers.po.StudentHealthFemale;

public interface StudentHealthFemaleMapper {
    StudentHealthFemale selectStudentHealthFemaleByStudentId(Integer studentId);
}
