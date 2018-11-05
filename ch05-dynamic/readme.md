## 动态sql
### if
```xml
 <select id="findRolesByRoleName" parameterType="string" resultMap="roleResultMap">
        select <include refid="role_columns"/>
        from role
        where 1=1
            <if test="roleName != null and roleName !=''">
                and name like concat('%',#{roleName},'%')
            </if>
 </select>
```
### choose/when/otherwise 
实现业务：   
* 角色编号不为空，使用编号查询
* 编号为空，角色名称不为空，使用角色名称查询
* 角色编号和角色名称都为空，则查询备注不为空的数据
```xml
<select id="findRoles" parameterType="role" resultMap="roleResultMap">
        select <include refid="role_columns"/>
        from role
        <where>
            <choose>
                <when test="id != null">
                    and id=#{id}
                </when>
                <when test="roleName != null and roleName !=''">
                    and name like concat('%',#{roleName},'%')
                </when>
                <otherwise>
                    and note is not null
                </otherwise>
            </choose>
        </where>
    </select>
```
### tirm / where / set
#### where
```xml
<select id="findRolesByName" resultMap="roleResultMap">
        select <include refid="role_columns"/>
        from role
        <where>
             name like concat('%',#{name},'%')
        </where>
    </select>
```
#### trim 
去掉sql中某些字符串,eg：or/and
```xml
 <select id="findRolesByName" resultMap="roleResultMap">
        select <include refid="role_columns"/>
        from role
        <trim prefix="where" prefixOverrides="and">
           <if test="name != null and name !=''">
            and name like concat('%',#{name},'%')
           </if>
        </trim>
    </select>
```
#### set 
更新某个自动使用eg:基本语句：UPDATE role SET note='ss',name='name'
```xml
 <update id="update" parameterType="role">
        update role
        <set>
            <if test="name != null and name !=''">
                name = #{name},
            </if>
            <if test="note != null and note !=''">
                note = #{note}
            </if>
        </set>
        where id = #{id}
    </update>
```
### foreact
循环元素，作用在于遍历集合
```xml
 <select id="findByIds" resultMap="roleResultMap">
        select <include refid="role_columns"/>
        from role
        <where>
            id in 
            <foreach collection="ids" item="id" index="index" open="(" separator="," close=")">
                #{id}
            </foreach>
        </where>
    </select>
```
### test
条件判断语句
### bind
自定义上下文变量，方便使用
```xml
    <select id="findRoleByBind" resultMap="roleResultMap">
        <bind name="pattern" value="'%' + _parameter + '%'"/>
        select <include refid="role_columns"/>
        from role
        where name like #{pattern}
    </select>

    <select id="findRoleByBinds" resultMap="roleResultMap">
        <bind name="pattern_name" value="'%' + name + '%'"/>
        <bind name="pattern_note" value="'%' + note + '%'"/>
        select <include refid="role_columns"/>
        from role
        where name like #{pattern_name}
        and note like #{pattern_note}
    </select>
```

