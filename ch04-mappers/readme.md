## 映射器
### 映射器的注意元素
#### 1.select元素
##### 自动映射
自动映射可以在setting元素中配置autoMappingBehavior属性值来设置其策略
+ NONE 取消自动映射
+ PARTIAL （默认）只会自动映射，没有定义嵌套结果集映射的结果集
+ FULL 会自动映射任意复杂的结果集（无论是否嵌套）
数据库规范下划线命名->PoJo驼峰命名：可以设置mapUnderscoreToCamelCase为true，来实现自动映射
##### 传递多个参数
方法一：使用paramterType="map"   
弊端：代码可读性差
```
 1.接口中
 /**
     * 【禁止使用】使用map传递参数
     * @param params
     * @return
     */
    List<Role> findRoleByMap(Map<String,String> params);
 2.xml中
   <select id="findRoleByMap" parameterType="map" resultMap="roleMap">
         select <include refid="role_columns"/> from t_role
         where role_name like concat('%',#{roleName},'%')
         and note like concat('%',#{note},'%')
   </select>
```
方法二：使用注解传递参数   
优点：代码可读性提高，xml中无须定义参数类型
弊端：传递参数过多时，可读性差
```
1.接口中
 /**
     * 【恰当使用】使用注解方式传递参数
     * @param name
     * @param note
     * @return
     */
    List<Role> findRoleByAnnotation(@Param("name")String name,@Param("note")String note);

2.xml中
 <select id="findRoleByAnnotation" resultMap="roleMap">
        select id,role_name,note from t_role
        where role_name like concat('%',#{roleName},'%')
        and note like concat('%',#{note},'%')
 </select>
```
方法三：使用JavaBean传递参数  
优点：参数多于5个时候，建议使用javabean 
```
1.定义javabean
2.接口中
 /**
     * 【推荐使用】使用JavaBean传递参数
     * @param role
     * @return
     */
    List<Role> findRoleByParams(Role role);
3.xml中
 <select id="findRoleByParams" parameterType="com.zccoder.mybatis1.ch4.mappers.po.Role" resultMap="roleMap">
        select id,role_name,note from t_role
        where role_name like concat('%',#{roleName},'%')
        and note like concat('%',#{note},'%')
 </select>
```   
##### 使用resultMap映射结果集   
```
1.定义
   <resultMap id="roleMap" type="com.zccoder.mybatis1.ch4.mappers.po.Role">
            <id column="id" property="id" javaType="long" jdbcType="BIGINT"/>
            <result column="role_name" property="roleName" javaType="string" jdbcType="VARCHAR"/>
            <result column="note" property="note" javaType="string" jdbcType="VARCHAR"/>
   </resultMap>
2.使用
  <select id="findRoleByAnnotation" resultMap="roleMap">
        select id,role_name,note from t_role
        where role_name like concat('%',#{roleName},'%')
        and note like concat('%',#{note},'%')
  </select>
```
#### 2.insert元素
##### 主键回填
```xml
    <!--插入后，自动回填JavaBean的id值-->
    <!--keyProperty:指定主键字段-->
    <!--useGeneratedKeys="true"：使用数据库策略生成主键-->
   <insert id="insert" parameterType="role" useGeneratedKeys="true" keyProperty="id">
        insert into t_role(role_name, note) value (#{roleName},#{note})
   </insert>
   
```
##### 主键自定义
eg:设置id为空时定义1，否则为最大值+2
```xml
   <insert id="insert" parameterType="role" useGeneratedKeys="true" keyProperty="id">
        <selectKey keyProperty="id" resultType="int" order="BEFORE">
            select if(max(id) is null,1,max(id)+2) as newId from t_role
        </selectKey>
        insert into t_role(role_name, note) value (#{roleName},#{note})
   </insert>
```
#### 3.update元素和delete元素
MyBatis执行完update元素和delete元素后会返回一个整数，标识执行后影响的记录条数
```
1.接口中
 /**
     * 更新
     * @param role
     */
    void update(Role role);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);
2.xml中
 <update id="update" parameterType="role">
        update t_role set role_name=#{roleName},note=#{note}
        where id=#{id}
 </update>
 
 <delete id="delete" parameterType="Long">
        delete from t_role where id=#{id}
 </delete>
```
#### 4.参数
注意：定义参数属性时候，不允许换行
##### 特殊字符传替换和处理（#和$）
```
eg:传递变量columns="col1,col2,col3..."给Sql,
   ->使用#，会变为"col1,col2,col3..."
   ->使用$，会直译，不会带引号
```
#### 5.Sql元素
定义sql的一部分，其他语句可以通过引用来使用   
1.sql的使用
```
   1.定义sql 
   <sql id="role_columns">
            id,role_name,note
   </sql>
   2.通过include标签引用定义的sql
   <select id="findRoleByMap" parameterType="map" resultMap="roleMap">
           select <include refid="role_columns"/> from t_role
           where role_name like concat('%',#{roleName},'%')
           and note like concat('%',#{note},'%')
   </select>
```
2.定制参数
```
   1.定义sql 
   <sql id="role_columns">
            #{prefix}.id,#{prefix}.role_name,#{prefix}.note
   </sql>
   2.通过include标签引用定义的sql
   <select id="findRoleByMap" parameterType="map" resultMap="roleMap">
           select 
           <include refid="role_columns"><property name="prefix" value="r"/></include> 
           from t_role r
           where role_name like concat('%',#{roleName},'%')
           and note like concat('%',#{note},'%')
   </select>
   3.使用refid
   <sql id="someinclude">
        select  * from <include refid="${tablename}"/>
   </sql>
```
#### 6.resultMap结果集映射器
resultMap定义的主要是一个结果级的映射，MyBatis现有的版本**只支持resultMap查询**，不支持更新或者保存，更不要说级联的更新/删除和修改了
##### resultMap元素构成
```xml
<resultMap>
    <constructor>
        <idArg/>
        <arg/>
    </constructor>
    <id/>
    <result/>
    <association/>
    <collection/>
    <discriminator>
        <case/>
    </discriminator>
</resultMap>
```
###### constructor 用于配置构造方法，一个pojo不可没有构造方法，因此构造方法要匹配
```
1.POJO->public RoleBean(Integer id,String roleName),要匹配这个构造方法才能使用这个实体类进行映射
 <constructor>
     <idArg column="id" javaType="long"></idArg>
     <arg column="roleName" javaType="string"></arg>
 </constructor>
```
##### 使用map存储结果集
一般而言，任何的select语句都可以使用map存储， ，更多的时候使用Pojo
##### 使用POJO存储结果集
##### 级联




















