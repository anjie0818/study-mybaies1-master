package com.zccoder.mybatis1.ch4.mappers.po;

import org.apache.ibatis.type.Alias;

@Alias("StudentLecture")
public class StudentLecture {
    private Integer id;
    private Integer studentId;
    private Integer lectureId;
    private Double grade;
    private String note;
    private Lecture lecture;

    @Override
    public String toString() {
        return "StudentLecture{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", lectureId=" + lectureId +
                ", grade=" + grade +
                ", note='" + note + '\'' +
                ", lecture=" + lecture +
                '}';
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
