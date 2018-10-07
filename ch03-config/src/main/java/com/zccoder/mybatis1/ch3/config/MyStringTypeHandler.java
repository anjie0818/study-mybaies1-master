package com.zccoder.mybatis1.ch3.config;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <br>
 * 标题：自定义 TypeHandler <br>
 * 描述：实现 TypeHandler 接口<br>
 * 使用 @MappedTypes 注解指定哪些 Java 类型被拦截
 * 使用 @MappedJdbcTypes 注解指定哪些 Jdbc 类型被拦截
 *
 * @author zc
 * @date 2018/03/16
 **/
@MappedTypes({String.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class MyStringTypeHandler implements TypeHandler<String> {

    private Logger log = Logger.getLogger(MyStringTypeHandler.class);


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        log.info("A--使用我的TypeHandler:preparedStatement--"+preparedStatement+"--i:"+i+"--s:"+s+"--jdbcType:"+jdbcType);
        preparedStatement.setString(i,s);
    }

    @Override
    public String getResult(ResultSet resultSet, String s) throws SQLException {
        log.info("B--使用我的TypeHandler:resultSet--"+resultSet+"--s:"+s);
        return resultSet.getString(s);
    }

    @Override
    public String getResult(ResultSet resultSet, int i) throws SQLException {
        log.info("C--使用我的TypeHandler:resultSet--"+resultSet+"--i:"+i);
        return resultSet.getString(i);
    }

    @Override
    public String getResult(CallableStatement callableStatement, int i) throws SQLException {
        log.info("D--使用我的TypeHandler:callableStatement--"+callableStatement+"--i:"+i);
        return callableStatement.getString(i);
    }
}
