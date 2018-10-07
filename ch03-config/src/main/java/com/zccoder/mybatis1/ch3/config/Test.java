package com.zccoder.mybatis1.ch3.config;

import com.zccoder.mybatis1.ch3.config.enums.Sex;
import com.zccoder.mybatis1.ch3.config.mapper.RoleMapper;
import com.zccoder.mybatis1.ch3.config.mapper.UserMapper;
import com.zccoder.mybatis1.ch3.config.po.Role;
import com.zccoder.mybatis1.ch3.config.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class Test {
    public static SqlSessionFactory getSqlSessionFactoryByXml(){
        String resource="mybatis-typeHandlers.cfg.xml";
        InputStream inputStream=null;
        try {
            inputStream= Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = Test.getSqlSessionFactoryByXml().openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user1 = mapper.getUser(33L);
        System.out.println(user1.getSex().getName());
        session.commit();
    }
}
