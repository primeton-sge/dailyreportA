package com.primeton.dailyreport.util;

import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Properties;

public class PythonUtil {
    public static void callPython(String username, String password, String projectid, String taskid, String content, String date) {
        try{
            String path = FileUtil.class.getClassLoader().
                    getResource("./templates/python/autosign.py").toURI().getPath();
            path = path.substring(0, path.length() - 11) + username + ".py";
            path = path.substring(1, path.length());
            File file = new File(path);
            if(!file.exists()) {
                FileUtil.makePythonFile(username, password, projectid, taskid, content, date);
            }
            String[] args = new String[]{"python", path};
            Process pr = Runtime.getRuntime().exec(args);
            pr.waitFor();
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(pr.getInputStream(), "GB2312"));
            BufferedReader ein = new BufferedReader(new
                    InputStreamReader(pr.getErrorStream(), "GB2312"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = ein.readLine()) != null) {
                System.out.println(line);
            }
            ein.close();
            in.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        callPython("kongyy", "Kyy950910.k", "5222", "49", "组件设计开发", "2018-08-27");
    }
}
