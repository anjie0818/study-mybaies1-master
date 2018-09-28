package com.zccoder.mybatis1.ch5.dynamic.mapper;


import com.zccoder.mybatis1.ch5.dynamic.po.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标题：Role DAO<br>
 * 描述：Role DAO<br>
 *
 * @author zc
 * @date 2018/04/25
 **/
public interface RoleMapper {

    /**
     * if、where、trim
     * @param name
     * @return
     */
    List<Role> findRolesByname(String name);

    /**
     * chose、when、otherwise
     * @param role
     * @return
     */
    List<Role> findRolesByname(Role role);

    /**
     * set、test
     * @param role
     */
    void update(Role role);

    /**
     * foreach
     * @param ids
     * @return
     */
    List<Role> findByIds(List<Long> ids);

    /**
     * bind
     * @param name
     * @return
     */
    List<Role> findRoleByBind(@Param("_parameter")String name);

    /**
     * 多个bind
     * @param name
     * @param note
     * @return
     */
    List<Role> findRoleByBinds(@Param("name")String name,@Param("note")String note);
}
