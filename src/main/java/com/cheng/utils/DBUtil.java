package com.cheng.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

    private static final String DRIVER = Env.getInstance().getProperty("dirver");
    private static final String URL = Env.getInstance().getProperty("url");
    private static final String USER = Env.getInstance().getProperty("user");
    private static final String PASS = Env.getInstance().getProperty("pass");

    public static Connection getConnection(){

        Connection conn = null;

        ComboPooledDataSource cds = new ComboPooledDataSource();

        try {
            cds.setJdbcUrl(URL);
            cds.setDriverClass(DRIVER);
            cds.setUser(USER);
            cds.setPassword(PASS);
            conn = cds.getConnection();
        } catch (PropertyVetoException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeAll(ResultSet rs, PreparedStatement ptst, Connection conn){
        try{
            if(rs != null){
                rs.close();
                rs = null;
            }
            if(ptst != null){
                ptst.close();
                ptst = null;
            }
            if(conn != null){
                conn.close();
                conn = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        System.out.println(Env.getInstance().getProperty("url"));
    }
}
