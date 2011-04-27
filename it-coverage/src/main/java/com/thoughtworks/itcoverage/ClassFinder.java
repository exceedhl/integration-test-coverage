package com.thoughtworks.itcoverage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {
    public List<Class> findAll(File dir, String pattern) throws MalformedURLException {
        ClassLoader cl = getClassLoader(dir);
        return findClasses(cl, dir, "", pattern);
    }

    private List<Class> findClasses(ClassLoader cl, File directory, String packageName, String pattern) {
        List<Class> classes = new ArrayList<Class>();
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(cl, file, getPackageName(packageName, file), pattern));
            } else if (file.getName().endsWith(".class") && file.getName().matches(pattern)) {
                try {
                    classes.add(cl.loadClass(getClassName(packageName, file)));
                } catch (ClassNotFoundException e) {
                    // ignore error and go to find next classes
                }
            }
        }
        return classes;
    }

    private String getPackageName(String packageName, File file) {
        if (!packageName.isEmpty()) {
            return packageName + "." + file.getName();
        }
        return file.getName();
    }

    private String getClassName(String packageName, File file) {
        String className = file.getName().substring(0, file.getName().length() - 6);
        if (!packageName.isEmpty()) {
            return packageName + '.' + className;
        }
        return className;
    }

    private ClassLoader getClassLoader(File dir) throws MalformedURLException {
        URL url = dir.toURL();
        URL[] urls = new URL[]{url};
        return new URLClassLoader(urls);
    }
}
