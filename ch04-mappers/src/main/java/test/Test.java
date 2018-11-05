package test;

import com.zccoder.mybatis1.ch4.mappers.mapper.*;
import com.zccoder.mybatis1.ch4.mappers.po.Student;
import com.zccoder.mybatis1.ch4.mappers.po.StudentHealthMale;
import com.zccoder.mybatis1.ch4.mappers.po.StudentMale;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

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
        SqlSessionFactory factory = Test.getSqlSessionFactoryByXml();
        SqlSession session = factory.openSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        mapper.selectStudentById(1);
        mapper.selectStudentById(1);
        session.commit();
        System.out.println("============");
        SqlSession session2 = factory.openSession();
        StudentMapper mapper2 = session2.getMapper(StudentMapper.class);
        mapper2.selectStudentById(1);
    }
}
