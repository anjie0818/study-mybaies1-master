## mybatis配置
Mybatis配置文件不能颠倒顺序，层次结构如下
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties/><!--配置属性-->
    <settings/><!--设置-->
    <typeAliases/><!--类型命名-->
    <typeHandlers/><!--类型处理器-->
    <objectFactory/><!--对象工厂-->
    <plugins/><!--插件-->
    <environments default=""><!--配置环境-->
        <environment id=""><!--环境变量-->
            <transactionManager type=""></transactionManager><!--事务管理器-->
            <dataSource type=""></dataSource><!--数据源-->
        </environment>
    </environments>
    <databaseIdProvider/><!--数据库厂商标识-->
    <mappers/><!--映射器-->
</configuration>
```   
### properties元素
properties是配置属性的元素，可以在配置文件上下文中使用配置的属性   
三种配置方式
* property子元素
* properties配置文件
* 程序参数传递
##### 优先级
+ XML文件中properties元素体内的属性首先被读取
+ 根据properties元素中resource属性读取类路径下的属性文件，并覆盖已经读取的同名属性
+ 读取作为方法参数传递的属性，并覆盖已同名的顺序   

因此，通过类中方法传递的属性优先级最高，resouce/url属性中指定的配置文件次之，最低优先级的是properties属性中指定的属性
+ 注意
  + 不要混合使用，会造成管理混乱
  + 首选properties配置文件
  + 如何对属性左特别的加密等，需要使用第三种方法
### 设置
设置（setting）是Mybatis中最复杂，最重要的配置，会改变Mybaits运行时行为。大部分时候我们不需要配置它，或者只需要配置少数几项即可。
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--MyBatis 的 settings 元素-->
    <!--参考官方文档地址【http://www.mybatis.org/mybatis-3/zh/configuration.html#settings】-->

    <settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="true"/>
        <setting name="multipleResultSetsEnabled" value="true"/>
        <setting name="useColumnLabel" value="true"/>
        <setting name="useGeneratedKeys" value="false"/>
        <setting name="autoMappingBehavior" value="PARTIAL"/>
        <setting name="defaultExecutorType" value="SIMPLE"/>
        <setting name="defaultStatementTimeout" value="25"/>
        <setting name="safeRowBoundsEnabled" value="false"/>
        <setting name="mapUnderscoreToCamelCase" value="false"/>
        <setting name="localCacheScope" value="SESSION"/>
        <setting name="jdbcTypeForNull" value="OTHER"/>
        <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
    </settings>
    
</configuration>
```
### 别名
别名（typeAliases）是一个指代的名称。可以在Mybatis上下文中使用，分为**系统定义别名**和**自定义别名**。不区分大小写   
生命周期：在解析配置文件的时候产生，然后长期保存在Configuration对象中。
##### 自定义别名
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!--MyBatis 的 typeAliases 元素-->
    <!--参考官方文档地址【http://www.mybatis.org/mybatis-3/zh/configuration.html#typeAliases】-->

    <!--【方式一】定义别名-->
    <!--<typeAliases>-->
        <!--<typeAlias type="com.zccoder.mybatis1.ch3.config.po.Role" alias="role"/>-->
    <!--</typeAliases>-->

    <!--【方式二】自动扫描-->
    <typeAliases>
        <package name="com.zccoder.mybatis1.ch3.config.po"/>
        <!--【说明】使用自动扫描时，对应的 PO 类可以使用 @Alias 注解指定别名。当没有使用 @Alias 指定别名时，默认为类名首字母小写-->
        <!--参考类【com.zccoder.mybatis1.ch3.config.po.Role】-->
    </typeAliases>
</configuration>
```
### typeHandlers类型处理器
Mybaits在预处理语句（PrepareStatement）中设置一个参数时，或者从结果集（ResultSet）中取出一个值时，都会用注册类的typeHandler进行处理。   
typeHandler常用的配置为Java类型（javaType）JDBC类型（jdbcType），typeHandler的作用就是进行这个种类型之间的转换。
##### 系统中定义的typeHandler
仓库：org.apache.ibatis.type.TypeHandlerRegistry   
注意： 1.数据类型的精度，数据库int double decimal和java的精度 长度都是不一样的
      2.时间进度，取数据到日用DateOnlyTypeHandler即可，用到精度为秒的用SQLTimestampTypeHandler。   
##### 自定义typeHandler
1.配置XML文件 
```xml
<typeHandlers>
        <!--注册自定义的 typeHandler-->
        <typeHandler handler="com.zccoder.mybatis1.ch3.config.MyStringTypeHandler" jdbcType="VARCHAR" javaType="string"/>
</typeHandlers>
```  
2.自定义实现类
```java
/**
 * <br>
 * 标题：自定义 TypeHandler <br>
 * 描述：实现 TypeHandler 接口<br>
 * 使用 @MappedTypes 注解指定哪些 Java 类型被拦截
 * 使用 @MappedJdbcTypes 注解指定哪些 Jdbc 类型被拦截--需要满足指代枚举类型
**/
@MappedTypes({String.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class MyStringTypeHandler implements TypeHandler<String> {

    private Logger log = Logger.getLogger(MyStringTypeHandler.class);

    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}

```   
3.修改Mapper映射器配置
* 三种方法
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.zccoder.mybatis1.ch3.config.mapper.RoleMapper">

    <resultMap id="roleMap" type="role">
        <!--定义结果类型转化器标识，才能使用类型转换器-->
        <id column="id" property="id" javaType="long" jdbcType="BIGINT"/>
        <!--方法一：在定义的javaType和jdbcType要和自定义的typeHandler是一致-->
        <result column="role_name" property="name" javaType="string" jdbcType="VARCHAR"/>
        <!--方法二：映射集直接定义具体的typeHandler-->
        <result column="note" property="note" javaType="string" jdbcType="VARCHAR" typeHandler="com.zccoder.mybatis1.ch3.config.MyStringTypeHandler"/>
    </resultMap>
    
    <select id="getRole" parameterType="Long" resultMap="roleMap">
        select id,name as role_name,note from role where id=#{id}
    </select>

    <select id="findRole" parameterType="string" resultMap="roleMap">
        <!--在参数中定义typeHandler-->
        select id,name as role_name,note from role where name like concat ('%',#{roleName javaType=string, jdbcType=VARCHAR, typeHandler=com.zccoder.mybatis1.ch3.config.MyStringTypeHandler},'%')
    </select>

    <insert id="insertRole" parameterType="role">
        insert into role(name, note) values (#{name},#{note})
    </insert>

    <delete id="deleteRole" parameterType="Long">
        delete from role where id=#{id}
    </delete>

</mapper>
```
##### 枚举类型typehandler
实现方法：
+ org.apache.ibatis.type.EnumTypeHandler--使用枚举字符串作为参数传递
+ org.apache.ibatis.type.EnumOrdinalTypeHandler--使用枚举整数下标作为参数
###### 方法一：使用EnumOrdinalTypeHandler
1. 创建枚举类
```java
/**
 * <br>
 * 标题：性别枚举<br>
 * 描述：对应数据库字段<br>
 *
 * @author zc
 * @date 2018/03/16
 **/
public enum Sex {

    /**
     * 数据字典
     */
    MALE(1, "男"),
    /**
     * 数据字典
     */
    FEMALE(2, "女");

    private int id;
    private String name;

    private Sex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Sex getSex(int id) {
        for (Sex sex : Sex.values()) {
            if (sex.id == id) {
                return sex;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

```
2. mybatis的config文件中进行注册
```xml
 <typeHandlers>
        <!--注册自定义的 typeHandler-->
        <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler" javaType="com.zccoder.mybatis1.ch3.config.enums.Sex"/>
    </typeHandlers>
```
3. userMapper.xml文件中使用
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.zccoder.mybatis1.ch3.config.mapper.UserMapper">

    <resultMap id="userMap" type="com.zccoder.mybatis1.ch3.config.po.User">
        <!--定义结果类型转化器标识，才能使用类型转换器-->
        <id column="id" property="id" javaType="long" jdbcType="BIGINT"/>

        <result column="userName" property="userName"/>
        <result column="cnname" property="cnname"/>
        <result column="birthday" property="birthday"/>
        <result column="sex" property="sex" typeHandler="org.apache.ibatis.type.EnumOrdinalTypeHandler"/>
        <result column="email" property="email"/>
        <result column="mobile" property="mobile"/>
        <result column="note" property="note"/>
    </resultMap>

    <select id="getUser" parameterType="Long" resultMap="userMap">
        select id,userName,cnname,birthday,sex,email,mobile,note from user where id=#{id}
    </select>

    <insert id="insertUser" parameterType="com.zccoder.mybatis1.ch3.config.po.User">
        insert into user(userName,cnname,birthday,sex,email,mobile,note) values (#{userName},#{cnname},#{birthday},#{sex,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler},#{email},#{mobile},#{note})
    </insert>

</mapper>
```
###### 方法二：使用EnumTypeHandler
同方法一，只需要修改EnumOrdinalTypeHandler->EnumTypeHandler
###### 方法三：使用自定义EnumTypeHandler
1.配置文件注册
```xml
 <typeHandlers>
        <!--注册自定义的 typeHandler-->
        <typeHandler handler="com.zccoder.mybatis1.ch3.config.SexEnumTypeHandler" javaType="com.zccoder.mybatis1.ch3.config.enums.Sex"/>
    </typeHandlers>
```
2.自定义类型转换器
```java
package com.zccoder.mybatis1.ch3.config;

import com.zccoder.mybatis1.ch3.config.enums.Sex;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 标题：自定义枚举类型typeHandler<br>
 * 描述：性别枚举转换<br>
 *
 * @author zc
 * @date 2018/04/19
 **/
public class SexEnumTypeHandler implements TypeHandler<Sex>{

    @Override
    public void setParameter(PreparedStatement ps, int index, Sex sex, JdbcType jdbcType) throws SQLException {
        ps.setInt(index,sex.getId());
    }

    @Override
    public Sex getResult(ResultSet rs, String columnName) throws SQLException {
        int id = rs.getInt(columnName);
        return Sex.getSex(id);
    }

    @Override
    public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        int id = rs.getInt(columnIndex);
        return Sex.getSex(id);
    }

    @Override
    public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int id = cs.getInt(columnIndex);
        return Sex.getSex(id);
    }
}

```
3.mapper文件中使用
### ObjectFactory
#### 方法一：使用默认org.apache.ibatis.reflection.factory.DefaultObjectFactroy
#### 方法二：自定义
##### 步骤1：mybatisconfig文件中配置
```xml
 <objectFactory type="com.zccoder.mybatis1.ch3.config.MyObjectFactory">
        <property name="name" value="MyObjectFactory"></property>
    </objectFactory>
```
##### 步骤2：自定义类实现DefaultObjectFactroy
```java
package com.zccoder.mybatis1.ch3.config;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Properties;

/**
 * 标题：自定义ObjectFactory<br>
 * 描述：继承DefaultObjectFactory来简化编程，大部分情况下，使用系统默认的即可<br>
 *
 * @author zc
 * @date 2018/04/19
 **/
public class MyObjectFactory extends DefaultObjectFactory{

    private static final long serialVersionUID = -6421137313378981965L;
    Logger log = Logger.getLogger(MyObjectFactory.class);

    @Override
    public <T> T create(Class<T> type) {
        log.info("使用定制对象工厂的create方法构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        log.info("使用定制对象工厂的create方法构建列表对象");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("使用定制对象工厂的setProperties方法设置属性");
        super.setProperties(properties);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}

```
### 插件
...
### environments配置环境
```xml
    <!--定义数据库信息，默认使用 dev 数据库构建环境-->
    <environments default="dev">
        <environment id="dev">
            <!--采用 JDBC 事务管理-->
            <transactionManager type="JDBC">
                <property name="autoCommit" value="false"/>
            </transactionManager>
            <!--配置数据库链接信息-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://47.98.132.196:3306/mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="anjie123B"/>
            </dataSource>
        </environment>
    </environments>
```
#### 分析节点
* environments 中的default，表明在缺省的情况下，默认启用的数据源配置
* environment 配置一个数据源的开始标签，属性id是数据源的唯一标识
* transactionManager 配置数据库事务，type属性有三种方法
    * JDBC 这个配置就是直接使用了JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务范围
    * MANAGED MyBatis自身不会去实现事务管理，而是让程序的容器如（JBOSS，Weblogic）来实现对事务的管理
    * 自定义 
* dataSource 配置数据源的连接信息，type属性有三种方法
    * UNPOOLED 非连接池数据库 这个数据源的实现只是每次请求时打开和关闭
    * POOLED 连接池数据库 这种数据源的实现利用“池”的概念将JDBC连接对象组织起来，避免了创建先的连接实例时所必须的初始化和认证时间。这是一种使得并发WEb应用快速响应请求的流行的处理方式。
    * JNDI JNDI数据源 这个数据源的实现是为了能在如EJB或应用服务器这类容器中使用，容器可以集中在外部配置数据源，然后放置一个JDNI上下文的引用
* property 元素配置参数   
### databaseIdProvider数据库厂商标识
...
### mappers
#### 引入映射器的方法
* 用文件路径引入：resouce="com/../roleMapper.xml"
* 用包名引入：name="包名"
* 用类注册引入：class="类名"
* 用userMpper.xml引入：url="url"



 
 








































































