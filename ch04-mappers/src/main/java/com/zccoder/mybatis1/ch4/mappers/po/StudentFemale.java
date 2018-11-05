package com.zccoder.mybatis1.ch4.mappers.po;

import org.apache.ibatis.type.Alias;

import java.util.List;
@Alias("StudentFemale")
public class StudentFemale extends Student{
    private List<StudentHealthFemale> studentHealthFemaleList;

    public List<StudentHealthFemale> getStudentHealthFemaleList() {
        return studentHealthFemaleList;
    }

    public void setStudentHealthFemaleList(List<StudentHealthFemale> studentHealthFemaleList) {
        this.studentHealthFemaleList = studentHealthFemaleList;
    }

    @Override
    public String toString() {
        return "StudentFemale{" +
                "studentHealthFemaleList=" + studentHealthFemaleList +
                '}';
    }
}
