package com.zccoder.mybatis1.ch4.mappers.po;

import org.apache.ibatis.type.Alias;

import java.util.List;
@Alias("StudentMale")
public class StudentMale extends Student{
 private List<StudentHealthMale> studentHealthMaleList;

    @Override
    public String toString() {
        return super.toString()+"===="+"StudentMale{" +
                "studentHealthMaleList=" + studentHealthMaleList +
                '}';
    }

    public List<StudentHealthMale> getStudentHealthMaleList() {
        return studentHealthMaleList;
    }

    public void setStudentHealthMaleList(List<StudentHealthMale> studentHealthMaleList) {
        this.studentHealthMaleList = studentHealthMaleList;
    }
}
