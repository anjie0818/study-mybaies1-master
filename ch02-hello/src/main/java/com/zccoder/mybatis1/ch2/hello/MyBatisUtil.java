package com.zccoder.mybatis1.ch2.hello;

import com.zccoder.mybatis1.ch2.hello.mapper.RoleMapper;
import com.zccoder.mybatis1.ch2.hello.mapper.RoleMapperAnno;
import com.zccoder.mybatis1.ch2.hello.po.Role;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * <br>
 * 标题：MyBatis 工具类<br>
 * 描述：建立 MyBatis 的工厂对象（SqlSessionFactory）<br>
 *
 * @author zc
 * @date 2018/03/14
 **/
public class MyBatisUtil {

    public static SqlSessionFactory  getSqlSessionFactoryByXml(){
        String resource="mybatis.cfg.xml";
        InputStream inputStream=null;
        try {
            inputStream=Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    public static SqlSessionFactory getSqlSessionFactoryByConf(){
        //构建数据库连接池
        PooledDataSource dataSource=new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://47.98.132.196:3306/vueblog");
        dataSource.setUsername("root");
        dataSource.setPassword("anjie123B");
        //创建数据库事物
        TransactionFactory transactionFactory=new JdbcTransactionFactory();
        //创建数据库运行环境
        Environment environment=new Environment("dev",transactionFactory,dataSource);
        //构建configuration对象
        Configuration configuration=new Configuration(environment);
        //注册一个mybaitis上下文别名
        configuration.getTypeAliasRegistry().registerAlias("roles",Role.class);
        //加入映射器
        configuration.addMapper(RoleMapper.class);
        configuration.addMapper(RoleMapperAnno.class);
        //使用sqlsessionFactoryBuilder构建sqlsessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}
