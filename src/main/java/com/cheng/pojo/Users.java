package com.cheng.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Users {

    private int uid;
    private String name;
    private String pass;
    private List<Role> role;
}
