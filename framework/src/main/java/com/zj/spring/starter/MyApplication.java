package com.zj.spring.starter;

import com.zj.spring.beans.BeanFactory;
import com.zj.spring.core.ClassScanner;
import com.zj.spring.web.handler.HandlerManager;
import com.zj.spring.web.server.TomcatServer;

import java.util.List;

/**
 * @author zhaoj
 * @version MyApplication.java, v 0.1 2020-01-13 11:07
 */
public class MyApplication {

    public static void run(Class<?> cls, String[] args) {
        System.out.println("Hello my-spring!");

        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            ClassScanner.scanClasses(cls.getPackage().getName());
            List<Class<?>> classList = ClassScanner.classList;
            BeanFactory.initBean(classList);
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach(it -> System.out.println(it.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
