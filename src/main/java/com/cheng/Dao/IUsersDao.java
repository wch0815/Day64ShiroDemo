package com.cheng.Dao;

import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.Users;

import java.util.List;

public interface IUsersDao {
    Users getUserByLogin(String user,String pass);

    List<Role> getAllRole(String username);

    List<Permission> getAllPermission(String username);
}
