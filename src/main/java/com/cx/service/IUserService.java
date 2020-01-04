package com.cx.service;

import com.cx.domain.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/2 15:39
 */
public interface IUserService extends UserDetailsService {
    UserInfo findMoney(String uid)throws Exception;
    void updateUser(UserInfo userInfo);

    void updatePassword(UserInfo userInfo);

    void trans(UserInfo userInfo,UserInfo user);

    int add(UserInfo userInfo);

    void addRole(String userId,String roleId);
}
