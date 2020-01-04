package com.cx.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/2 10:42
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(Model model){
        System.out.println("test");
        return "list";
    }
}
