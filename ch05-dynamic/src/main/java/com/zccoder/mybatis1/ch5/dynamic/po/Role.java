package com.zccoder.mybatis1.ch5.dynamic.po;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 标题：角色类<br>
 * 描述：POJO<br>
 *
 * @author zc
 * @date 2018/04/25
 **/
@Alias("role")
public class Role implements Serializable {


    private Long id;
    private String name;
    private String note;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }}
