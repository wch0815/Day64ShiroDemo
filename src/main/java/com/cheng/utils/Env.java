package com.cheng.utils;

import java.io.IOException;
import java.util.Properties;

public class Env extends Properties {

    private Env(){
        try {
            load(getClass().getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Env getInstance(){
        return new Env();
    }
}
