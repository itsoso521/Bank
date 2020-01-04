package com.cx.utils;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/3 14:16
 */
public class LogUtil {

    public static String  isAction(String action){
           if(action.contains("sea")){
               return "查询";
           }
           else if(action.contains("tran")){
              return "转账";
           }else if(action.contains("take")){
              return "取款";
           }else if(action.contains("add")){
               return "存款";
           }else{
               return "其他操作";
           }

    }
}
