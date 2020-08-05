package com.cheng.service;

import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.Users;

import java.util.List;

public interface IUsersService {
    Users getUserByLogin(String user,String pass);

    List<Role> getAllRole(String username);

    List<Permission> getAllpermission(String username);
}
