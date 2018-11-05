package com.zccoder.mybatis1.ch4.mappers.po;

import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;
@Alias("StudentHealthFemale")
public class StudentHealthFemale {
    private Integer id;
    private Integer studentId;
    private Timestamp checkDate;
    private String heart;
    private String liver;
    private String spleen;
    private String lung;
    private String kidney;
    private String uterus ;
    private String note;

    @Override
    public String toString() {
        return "Student_Health_Female{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", checkDate=" + checkDate +
                ", heart='" + heart + '\'' +
                ", liver='" + liver + '\'' +
                ", spleen='" + spleen + '\'' +
                ", lung='" + lung + '\'' +
                ", kidney='" + kidney + '\'' +
                ", uterus='" + uterus + '\'' +
                ", note='" + note + '\'' +
                '}';
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

    public Timestamp getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Timestamp checkDate) {
        this.checkDate = checkDate;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getLiver() {
        return liver;
    }

    public void setLiver(String liver) {
        this.liver = liver;
    }

    public String getSpleen() {
        return spleen;
    }

    public void setSpleen(String spleen) {
        this.spleen = spleen;
    }

    public String getLung() {
        return lung;
    }

    public void setLung(String lung) {
        this.lung = lung;
    }

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getUterus() {
        return uterus;
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
