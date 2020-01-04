package com.cx.dao;

import com.cx.domain.SysLog;
import com.cx.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/3 13:40
 */
public interface ISysLogDao {
    /**
     * 查看所有日志
     * @return
     * @throws Exception
     */
    //多表操作
    @Select("select * from sysLog where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "visitTime", column = "visitTime"),
            @Result(property = "username", column = "username"),
            @Result(property = "ip", column = "ip"),
            @Result(property = "url", column = "url"),
            @Result(column="executionTime",property="executionTime"),
            @Result(column="method",property="method"),
            @Result(column="money",property="money"),
            @Result(column="action",property="action")
    })
    public List<SysLog> findAll(UserInfo userInfo) throws Exception;

    /**
     *
     *  保存日志
     * @param sysLog
     * @throws Exception
     */
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method,action,money) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method},#{action},#{money})")
    public void save(SysLog sysLog) throws Exception;
}
