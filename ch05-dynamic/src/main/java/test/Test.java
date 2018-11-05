package test;

import com.zccoder.mybatis1.ch5.dynamic.mapper.RoleMapper;
import com.zccoder.mybatis1.ch5.dynamic.po.Role;
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
        RoleMapper mapper = session.getMapper(RoleMapper.class);
        Role role=new Role();
        role.setId(1L);
        role.setName("anjie");
        role.setNote("note");
        mapper.update(role);

    }
}
