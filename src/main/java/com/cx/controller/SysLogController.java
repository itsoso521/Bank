package com.cx.controller;

import com.cx.domain.SysLog;
import com.cx.domain.UserInfo;
import com.cx.service.ISysLogService;
import com.cx.service.IUserService;
import com.cx.service.impl.SysLogServiceImpl;
import com.github.pagehelper.PageInfo;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/3 13:50
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
   @Autowired
    private ISysLogService sysLogService;
   @Autowired
   private IUserService userService;
   @RequestMapping("/findAll.do")
   public ModelAndView findAll(Principal principal,@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "5") Integer pageSize) throws Exception {
       ModelAndView mv=new ModelAndView();
       String username=principal.getName();
       UserInfo userInfo=userService.findMoney(username);
       List<SysLog> list=sysLogService.findAll(page,pageSize,userInfo);
       //PageInfo就是一个分页Bean
       PageInfo pageInfo = new PageInfo(list);
       mv.setViewName("syslog-list");
       mv.addObject("pageInfo",pageInfo);
       System.out.println(list);
       return mv;
   }

}
