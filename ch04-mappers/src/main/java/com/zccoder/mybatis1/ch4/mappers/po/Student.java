package com.zccoder.mybatis1.ch4.mappers.po;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.List;

@Alias("Student")
public class Student implements Serializable{
    private Integer id;
    private String cnname;
    private Sex sex;
    private Integer selfcardNo;
    private String note;
    private StudentSelfcard studentSelfcard;
    private List<StudentLecture> studentLectureList;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", cnname='" + cnname + '\'' +
                ", sex=" + sex +
                ", selfcardNo=" + selfcardNo +
                ", note='" + note + '\'' +
                ", studentSelfcard=" + studentSelfcard +
                ", studentLectureList=" + studentLectureList +
                '}';
    }

    public List<StudentLecture> getStudentLectureList() {
        return studentLectureList;
    }

    public void setStudentLectureList(List<StudentLecture> studentLectureList) {
        this.studentLectureList = studentLectureList;
    }

    public StudentSelfcard getStudentSelfcard() {
        return studentSelfcard;
    }

    public void setStudentSelfcard(StudentSelfcard studentSelfcard) {
        this.studentSelfcard = studentSelfcard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getSelfcardNo() {
        return selfcardNo;
    }

    public void setSelfcardNo(Integer selfcardNo) {
        this.selfcardNo = selfcardNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
