package com.cx.controller;

import com.cx.domain.Message;
import com.cx.domain.UserInfo;
import com.cx.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/2 17:06
 */
@Controller
@RequestMapping("/user")
public class UserCongtroller {

      @Autowired
      private UserServiceImpl userService;
      @RequestMapping("/money.do")
      public ModelAndView searcMoney(Principal principal) throws Exception {
          ModelAndView mv=new ModelAndView();
          String username=principal.getName();
          System.out.println(username);
          UserInfo user=userService.findMoney(username);
          mv.addObject("user",user);
          mv.setViewName("user-show");
          return mv;
      }
    @RequestMapping("/add.do")
    public String addMoney(String money,Principal principal) throws Exception {
        ModelAndView mv=new ModelAndView();
        String username=principal.getName();
        UserInfo userInfo=userService.findMoney(username);
        userInfo.setBalance(userInfo.getBalance()+Double.parseDouble(money));
        System.out.println(userInfo.getBalance());
        userService.updateUser(userInfo);
        return  "redirect:money.do";
    }
    @RequestMapping("/take.do")
    public String takeMoney(String money,Principal principal) throws Exception {
        ModelAndView mv=new ModelAndView();
        String username=principal.getName();
        UserInfo userInfo=userService.findMoney(username);
        userInfo.setBalance(userInfo.getBalance()-Double.parseDouble(money));
        System.out.println(userInfo.getBalance());
        userService.updateUser(userInfo);
        return  "redirect:money.do";
    }
    @RequestMapping(value = "/password.do",method = RequestMethod.POST)
    public String Updatepassword(String password,Principal principal) throws Exception {
        ModelAndView mv=new ModelAndView();
        String username=principal.getName();
        UserInfo userInfo=userService.findMoney(username);
        userInfo.setPassword(password);
        userService.updatePassword(userInfo);
        return  "redirect:money.do";
    }

    @RequestMapping(value = "/search.do",method = RequestMethod.GET)
    @ResponseBody
    public  UserInfo searchUser(String trans) throws Exception {
            UserInfo userInfo=null;
            userInfo=userService.findMoney(trans);
        System.out.println(trans);
            System.out.println(userInfo);
            return userInfo;
    }


    @RequestMapping(value = "/search2.do",method = RequestMethod.GET)
    @ResponseBody
    public  UserInfo searUser(Principal principal) throws Exception {
        String username=principal.getName();
        UserInfo userInfo=userService.findMoney(username);
        return userInfo;
    }

    @RequestMapping(value = "/user.do",method = RequestMethod.GET)
    @ResponseBody
    public Message search(String trans) throws Exception {
        Message message=new Message();
        message.setMsg("查询用户是否存在！");
        UserInfo userInfo=userService.findMoney(trans);
        if(userInfo==null){
            message.setStatus(0);
        }else{
            message.setStatus(1);
        }
        return message;
    }
    @RequestMapping("/transer.do")
    public String trans(String money,String trans,Principal principal) throws Exception {
         // 用户
        String username=principal.getName();
        UserInfo userInfo=userService.findMoney(username);
        // 转账用户
        UserInfo user=userService.findMoney(trans);
        user.setBalance(user.getBalance()+Double.parseDouble(money));
        userInfo.setBalance(userInfo.getBalance()-Double.parseDouble(money));
        userService.trans(userInfo,user);
        return  "redirect:money.do";
    }

    @RequestMapping("/add2.do")
    public String  add(String username,String password,String email) throws Exception {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setEmail(email);
        userInfo.setFlag(1);
        int n=userService.add(userInfo);
        if(n==1){
            UserInfo userInfo1=userService.findMoney(username);
            userService.addRole(userInfo1.getId()+"",1+"");
        }
        return  "redirect:money.do";
    }
}
