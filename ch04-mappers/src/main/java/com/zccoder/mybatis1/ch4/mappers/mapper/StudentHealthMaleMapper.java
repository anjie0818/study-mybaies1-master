package com.zccoder.mybatis1.ch4.mappers.mapper;

import com.zccoder.mybatis1.ch4.mappers.po.StudentHealthMale;

public interface StudentHealthMaleMapper {
    StudentHealthMale selectStudentHealthMaleByStudentId(Integer studentId);
}
