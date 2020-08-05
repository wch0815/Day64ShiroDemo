package com.cheng.shiro;

import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.Users;
import com.cheng.service.IUsersService;
import com.cheng.service.impl.UsersServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    private IUsersService usersService = new UsersServiceImpl();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String username = getAvailablePrincipal(principals).toString();

        List<Role> roles = usersService.getAllRole(username);

        for (Role r: roles ) {
            info.addRole(r.getRname());
        }

        List<Permission> permissions = usersService.getAllpermission(username);

        for (Permission p: permissions) {
            info.addStringPermission(p.getPname());
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        AuthenticationInfo info = null;

        UsernamePasswordToken upt = (UsernamePasswordToken) token;

        String username = upt.getUsername();
        char[] password = upt.getPassword();

        String pass = new String(password);

        Users user = usersService.getUserByLogin(username, pass);
        if(user != null && user.getUid() != 0){
            info = new SimpleAuthenticationInfo(username,pass,getName());
        }

        return info;
    }
}
