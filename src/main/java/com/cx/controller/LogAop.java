package com.cx.controller;


import com.cx.domain.SysLog;
import com.cx.service.ISysLogService;
import com.cx.utils.LogUtil;
import com.mchange.v2.log.LogUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/3 14:01
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;
    private Class clazz;
    private Method method;


    @Before("execution(* com.cx.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        Class[] parameterTypes = ((MethodSignature)jp.getSignature()).getMethod().getParameterTypes();

        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
               // /* // classArgs[i] = args[i].getClass();*/
            }
           method=clazz.getMethod(methodName, parameterTypes);
        }
    }

    //后置通知
    @After("execution(* com.cx.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长

        String url = "";
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //获取访问的ip
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user=null;
                    try{
                        user = (User) context.getAuthentication().getPrincipal();
                    }catch (Exception e){
                        user=null;

                    }
                    if(user==null)return;
                    String username = user.getUsername();
                    Object[] args = jp.getArgs();
                    ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
                    // 参数名
                    String[] parameterNames = pnd.getParameterNames(method);
                    // 通过map封装参数和参数值
                    HashMap<String, Object> paramMap = new HashMap();
                    for (int i = 0; i < parameterNames.length; i++) {
                        paramMap.put(parameterNames[i], args[i]);
                    }
                    //将日志相关信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    sysLog.setAction(LogUtil.isAction(method.getName()));
                    double money=0;
                    Object object=paramMap.get("money");
                    if(object!=null){
                        money=Double.parseDouble(object+"");
                    }
                    sysLog.setMoney(money);
                    //调用Service完成操作
                    if(sysLog.getAction().equals("其他操作")){
                        return;
                    }else{
                        sysLogService.save(sysLog);
                    }
                }
            }
        }

    }
}
