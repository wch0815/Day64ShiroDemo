package com.cheng.testUserService;

import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.Users;
import com.cheng.service.IUsersService;
import com.cheng.service.impl.UsersServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.util.List;

public class testLogin {

    private IUsersService usersService = new UsersServiceImpl();

    @Test
    public void getUserByLogin(){
        Users user = usersService.getUserByLogin("zhangsan", "121212");
        System.out.println(user);
        System.out.println("成功");
    }

    @Test
    public void md5hash(){

        String raw = "123123";
        String password = new Md5Hash(raw,"wang").toString();
        System.out.println(password);
    }

    @Test
    public void testGetALlRole(){
        List<Role> role = usersService.getAllRole("lisi");
        System.out.println(role);
    }

    @Test
    public void testGetAllPermission(){
        List<Permission> list = usersService.getAllpermission("zhangsan");
        for (Permission p: list) {
            System.out.println(p);
        }
    }
}
