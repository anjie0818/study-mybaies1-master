package com.zccoder.mybatis1.ch4.mappers.po;

import org.apache.ibatis.type.Alias;

@Alias("Lecture")
public class Lecture {
    private Integer id;
    private String lectureName;
    private String note;

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", lectureName='" + lectureName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
