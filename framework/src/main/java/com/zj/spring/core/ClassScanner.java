/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.zj.spring.core;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author zhaoj
 * @version ClassScanner.java, v 0.1 2020-01-13 13:22
 */
public class ClassScanner {

    public static List<Class<?>> classList = new ArrayList<>();

    public static /*List<Class<?>> */ void scanClasses(String packageName) throws IOException, ClassNotFoundException {
        //List<Class<?>> classList = new ArrayList<>();
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            // file:/D:/IdeaProjects/my-spring/test/target/classes/com/zj/spring
            if (resource.getProtocol().contains("jar")) {
                JarURLConnection jarURLConnection = (JarURLConnection) resource.openConnection();
                String jarFilePath = jarURLConnection.getJarFile().getName();
                classList.addAll(getClassesFromJar(jarFilePath, path));
            } else if (resource.getProtocol().contains("file")) {
                System.out.println("以file开头的类文件");
                File f = new File(resource.getPath());
                //classList.addAll(func(f));
                func(f);
            } else {
                System.out.println("未找到符合条件的类路径");
            }
        }
       // return classList;
    }

    private static /*List<Class<?>>*/ void func(File file) throws ClassNotFoundException {
        // List<Class<?>> classes = new ArrayList<>();
        File[] files = file.listFiles();
        for (File f : files) {
            //若是目录，则递归打印该目录下的文件
            if (f.isDirectory()) {
                func(f);
            }
            if (f.isFile()) {
                //若是文件，转换成全路径Class类
                String classPath = f.getPath();
                if (classPath.endsWith(".class")) {
                    String classFullName = classPath.replace("\\", ".").substring(0, classPath.length() - 6);
                    System.out.println(classFullName);
                    int index = classFullName.indexOf("classes") + 8;
                    // System.out.println(index);
                    // D:.IdeaProjects.my-spring.test.target.classes.com.zj.spring.Application
                    System.out.println(classFullName.substring(index));
                    System.out.println("-----------------------------");
                    classList.add(Class.forName(classFullName.substring(index)));
                }
            }
        }
        // return classList;
    }


    private static List<Class<?>> getClassesFromJar(String jarFilePath, String path) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        JarFile jarFile = new JarFile(jarFilePath);
        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            // com/zj/spring/controller/TestController.class
            String entryName = jarEntry.getName();
            if (entryName.startsWith(path) && entryName.endsWith(".class")) {
                String classFullName = entryName.replace("/", ".").substring(0, entryName.length() - 6);
                classes.add(Class.forName(classFullName));
            }
        }
        return classes;
    }
}
