package com.zccoder.mybatis1.ch4.mappers.mapper;

import com.zccoder.mybatis1.ch4.mappers.po.StudentSelfcard;

public interface  StudentSelfcardMapper {
    StudentSelfcard findStudentSelfcardByStudentId(String studentId);
}
