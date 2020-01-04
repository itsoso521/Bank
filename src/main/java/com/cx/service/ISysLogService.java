package com.cx.service;

import com.cx.domain.SysLog;
import com.cx.domain.UserInfo;

import java.util.List;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/3 13:43
 */
public interface ISysLogService {
    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(int page,int pagesize,UserInfo userInfo) throws Exception;
}
