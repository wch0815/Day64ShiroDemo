package com.cheng.service.impl;

import com.cheng.Dao.IUsersDao;
import com.cheng.Dao.impl.UsersDaoImpl;
import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.Users;
import com.cheng.service.IUsersService;

import java.util.List;

public class UsersServiceImpl implements IUsersService {

    private IUsersDao usersDao = new UsersDaoImpl();

    @Override
    public Users getUserByLogin(String user, String pass) {
        return usersDao.getUserByLogin(user,pass);
    }

    @Override
    public List<Role> getAllRole(String username) {
        return usersDao.getAllRole(username);
    }

    @Override
    public List<Permission> getAllpermission(String username) {
        return usersDao.getAllPermission(username);
    }
}
