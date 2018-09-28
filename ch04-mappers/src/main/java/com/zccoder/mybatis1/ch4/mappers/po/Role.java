package com.zccoder.mybatis1.ch4.mappers.po;

import java.io.Serializable;

/**
 * 标题：角色类<br>
 * 描述：POJO<br>
 *
 * @author zc
 * @date 2018/04/25
 **/
public class Role implements Serializable {

    private static final long serialVersionUID = 7347474424837359046L;

    private Long id;
    private String name;
    private String note;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
