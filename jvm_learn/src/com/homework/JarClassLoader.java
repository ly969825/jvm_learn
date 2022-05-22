package com.homework;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarClassLoader extends ClassLoader {

    public JarFile jarFile;

    public ClassLoader parent;

    public JarClassLoader(JarFile jarFile) {
        super(Thread.currentThread().getContextClassLoader());
        this.parent = Thread.currentThread().getContextClassLoader();
        this.jarFile = jarFile;
    }


    public JarClassLoader(JarFile jarFile, ClassLoader parent) {
        super(parent);
        this.parent = parent;
        this.jarFile = jarFile;
    }
    
    public String classNameToJarEntry(String name){
        String s = name.replaceAll("\\.", "\\/");
        StringBuilder stringBuilder = new StringBuilder(s);
        stringBuilder.append(".class");
        return stringBuilder.toString();

    }
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            Class c = null;
            if (null != jarFile) {
                String jarEntryName = classNameToJarEntry(name);
                JarEntry entry = jarFile.getJarEntry(jarEntryName);
                if (null != entry) {
                    InputStream is = jarFile.getInputStream(entry);
                    int availableLen = is.available();
                    int len = 0;
                    byte[] bt1 = new byte[availableLen];
                    while (len < availableLen) {
                        len += is.read(bt1, len, availableLen - len);
                    }
                    c = defineClass(name, bt1, 0, bt1.length);
                } else {
                    if (parent != null) {
                        return parent.loadClass(name);
                    }
                }
            }
            return c;
        } catch (IOException e) {
            throw new ClassNotFoundException("Class " + name + " not found.");
        }
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        InputStream is = null;
        try {
            if (null != jarFile) {
                JarEntry entry = jarFile.getJarEntry(name);
                if (entry != null) {
                    is = jarFile.getInputStream(entry);
                }
                if (is == null) {
                    is = super.getResourceAsStream(name);
                }
            }
        } catch (IOException e) {
            // logger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return is;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JarClassLoader jarClassLoader = new JarClassLoader(new JarFile(new File("D:\\test\\test.jar")));

        Enumeration<JarEntry> entries = jarClassLoader.jarFile.entries();
        while (entries.hasMoreElements()) {
            String name = entries.nextElement().getName();
            System.out.println("name: " + name);
        }
        Class clazz1 = jarClassLoader.loadClass("User");
        Object obj1 = clazz1.newInstance();
        Method method1 = clazz1.getDeclaredMethod("sout", null);
        method1.invoke(obj1, null);
        System.out.println(clazz1.getClassLoader().getClass().getName());
    }
}