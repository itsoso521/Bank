package com.cx.service.impl;

import com.cx.dao.ISysLogDao;
import com.cx.domain.SysLog;
import com.cx.domain.UserInfo;
import com.cx.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/3 13:47
 */
@Service("sysLogServerImpl")
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
         sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page, int pageSize,UserInfo userInfo) throws Exception {
        PageHelper.startPage(page,pageSize);
        return sysLogDao.findAll(userInfo);
    }
}
