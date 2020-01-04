package com.cx.dao;

import com.cx.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/2 15:52
 */
@Component
public interface IUserDao {
    @Select("select * from users  where username=#{username}")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.cx.dao.IRoleDao.findRoleByUserId"))

    })
    public UserInfo findByUserName(String username)throws Exception;


    @Update("update users set balance=#{balance} where username=#{username}")
    public void updateUser(UserInfo userInfo);

    @Update("update users set password=#{password} where username=#{username}")
    public void updatePassword(UserInfo userInfo);

    @Insert("insert into users (username,password,email,flag) values(#{username},#{password},#{email},#{flag})")
    public int  add(UserInfo userInfo);



}
