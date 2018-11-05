package com.zccoder.mybatis1.ch4.mappers.po;

import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
@Alias("StudentSelfcard")
public class StudentSelfcard {
    private  int id;
    private  String studentId;
    //遇到java关键字，使用加"_"
    private  String native_;
    //发证日期,日期精确度高
    private Timestamp issueDate;
    //结束日期,日期精确度高
    private Timestamp endDate;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getNative_() {
        return native_;
    }

    public void setNative_(String native_) {
        this.native_ = native_;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentSelfcard{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", native_='" + native_ + '\'' +
                ", issueDate=" + issueDate +
                ", endDate=" + endDate +
                ", note='" + note + '\'' +
                '}';
    }
}
