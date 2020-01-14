package com.zj.spring;

import com.zj.spring.starter.MyApplication;

/**
 * @author zhaoj
 * @version Application.java, v 0.1 2020-01-13 10:56
 */
public class Application {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MyApplication.run(Application.class, args);
    }
}
