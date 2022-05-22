package com.jvm;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 反射调用addURL方法添加jar
 */
public class JvmAppClassLoaderAddURL {
    public static void main(String[] args) {
        String appPath = "file:d:/demo/";
        URLClassLoader classLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method addUrl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            addUrl.setAccessible(true);
            URL url = new URL(appPath);
            addUrl.invoke(classLoader, url);
            Class.forName("com.company.Hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
