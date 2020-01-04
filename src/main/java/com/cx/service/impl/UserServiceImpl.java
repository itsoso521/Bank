package com.cx.service.impl;

import com.cx.dao.IRoleDao;
import com.cx.dao.IUserDao;
import com.cx.domain.Role;
import com.cx.domain.UserInfo;
import com.cx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author 150552
 * @date 2020/1/2 15:40
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        System.out.println(username);
        try{
            userInfo=userDao.findByUserName(username);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(userInfo);
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getFlag() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }
    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public UserInfo findMoney(String username) throws Exception {
        return userDao.findByUserName(username);
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        userDao.updateUser(userInfo);
    }

    @Override
    public void updatePassword(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.updatePassword(userInfo);
    }

    @Override
    public void trans(UserInfo userInfo, UserInfo user) {
            userDao.updateUser(userInfo);
            userDao.updateUser(user);
    }

    @Override
    public int add(UserInfo userInfo) {
        System.out.println(userInfo.getPassword());
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));

        return  userDao.add(userInfo);
    }

    @Override
    public void addRole(String userId, String roleId) {
        roleDao.addRoleToUser(userId,roleId);
    }
}
