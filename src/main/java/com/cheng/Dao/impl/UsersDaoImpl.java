package com.cheng.Dao.impl;

import com.cheng.Dao.IUsersDao;
import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.Users;
import com.cheng.utils.DBUtil;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements IUsersDao {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public Users getUserByLogin(String user, String pass) {

        Users u = null;

        conn = DBUtil.getConnection();
        String sql = "select * from tb_user where username = ? and password = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,user);
            ps.setString(2,new Md5Hash(pass,"wang").toString());

            rs = ps.executeQuery();

            if (rs != null){
                u = new Users();
                if (rs.next()){
                    u.setUid(rs.getInt(1));
                    u.setName(rs.getString(2));
                    u.setPass(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs,ps,conn);
        }

        return u;
    }

    @Override
    public List<Role> getAllRole(String username) {

        List<Role> list = null;
        
        conn = DBUtil.getConnection();
        String sql = "select  r.* \n" +
                "\tfrom tb_user u,tb_role r,tb_user_role ur \n" +
                "\twhere u.uid = ur.uid \n" +
                "\tand ur.rid = r.rid  \n" +
                "\tand username=?;";
        try {
            ps = conn.prepareStatement(sql);

            ps.setString(1,username);

            rs = ps.executeQuery();

            if(rs != null){
                list = new ArrayList<>();
                Role r = null;
                while (rs.next()){

                    r = new Role(rs.getInt(1),rs.getString(2),null);

                    list.add(r);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs,ps,conn);
        }
        return list;
    }

    @Override
    public List<Permission> getAllPermission(String username) {

        List<Permission> list = null;

        conn = DBUtil.getConnection();

        String sql = "select p.* \n" +
                "\tfrom tb_user u,tb_role r,tb_user_role ur,tb_permisson p,tb_role_permisson rp\n" +
                "\twhere u.uid = ur.uid \n" +
                "\tand ur.rid = r.rid \n" +
                "\tand p.pid = rp.pid \n" +
                "\tand username=?;";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);

            rs = ps.executeQuery();
            if (rs!=null){
                Permission p = null;
                list = new ArrayList<>();
                while (rs.next()){
                    p = new Permission();
                    p.setPid(rs.getInt(1));
                    p.setPname(rs.getString(2));

                    list.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll(rs,ps,conn);
        }
        return list;
    }
}
