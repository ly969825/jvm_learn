package com.company;

import sun.misc.Launcher;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * 打印类加载器
 */
public class JvmClassLoadPrintPath {

    public static void main(String[] args) {
        //获取启动加载器
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器");
        for (URL url : urls) {
            System.out.println("====>" + url.toExternalForm());
        }
        //获取扩展类加载器
        printClassLoader("扩展类加载器",JvmClassLoadPrintPath.class.getClassLoader().getParent());

        //获取应用类加载器
        printClassLoader("应用类加载器",JvmClassLoadPrintPath.class.getClassLoader());
    }

    public static void printClassLoader(String name, ClassLoader classLoader) {
        if (classLoader != null) {
            System.out.println(name + "ClassLoader" + classLoader.toString());
            printURLForClassLoader(classLoader);
        } else {
            System.out.println(name + "ClassLoader -> null");
        }
    }

    public static void printURLForClassLoader(ClassLoader classLoader) {
        Object ucp = insightFiled(classLoader, "ucp");
        Object path = insightFiled(ucp, "path");
        ArrayList ps = (ArrayList) path;
        for (Object obj : ps) {
            System.out.println("====>" + obj.toString());
        }
    }

    public static Object insightFiled(Object obj, String fname) {
        Field field = null;
        try {
            if (obj instanceof URLClassLoader) {
                field = URLClassLoader.class.getDeclaredField(fname);
            } else {
                field = obj.getClass().getDeclaredField(fname);
            }
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
