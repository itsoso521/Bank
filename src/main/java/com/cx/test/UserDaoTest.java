package com.cx.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/2 22:04
 */

public class UserDaoTest {

    public static void main(String[] args) {
               test();
    }

    public static void test(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str="2019-09-10";
        try {
            // 字符串转化为时间
            Date date=simpleDateFormat.parse(str);
            System.out.println(date);
            // 时间类型转化为字符串
            String dateStr=simpleDateFormat.format(date);
            System.out.println(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
