package com.zccoder.mybatis1.ch4.mappers.mapper;

import com.zccoder.mybatis1.ch4.mappers.po.Lecture;

public interface LectureMapper {
    Lecture selectLectureById(Integer id);
}
