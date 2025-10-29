package com.prometheus.web;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
        }
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.parseInt(port));
        
        // Set the base directory
        String webappDirLocation = "src/main/webapp/";
        Context ctx = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        
        // Add the servlet classes to the classpath
        ctx.setParentClassLoader(Main.class.getClassLoader());
        
        tomcat.start();
        tomcat.getServer().await();
    }
}
