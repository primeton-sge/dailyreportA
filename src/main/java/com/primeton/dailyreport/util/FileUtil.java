package com.primeton.dailyreport.util;

import java.io.*;

public class FileUtil {
    public static void makePythonFile(String username, String password, String projectid, String taskid, String content, String date) {
        try{
            String inputPath = FileUtil.class.getClassLoader().
                    getResource("./templates/python/autosign.py").toURI().getPath();
            String outputPath = inputPath.substring(0, inputPath.length() - 11);

            File temp = new File(inputPath);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(temp));
            BufferedReader br = new BufferedReader(inputStreamReader);

            File file = new File(outputPath + username + ".py");
            if(!file.exists()) {
                file.createNewFile();
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);

            // 按行读取字符串
            int i = 1;
            String str;
            while((str = br.readLine()) != null) {
                if(i == 10) {
                    bw.write("id = '" + username + "'" + "\r\n");
                } else if(i == 11) {
                    bw.write("password = '" + password + "'" + "\r\n");
                } else if(i == 12) {
                    bw.write("projectid = '" + projectid + "'" + "\r\n");
                } else if(i == 13) {
                    bw.write("taskid = '" + taskid + "'" + "\r\n");
                } else if(i == 14) {
                    bw.write("content = '" + content + "'" + "\r\n");
                } else if(i == 15) {
                    bw.write("date = '" + date + "'" + "\r\n");
                } else {
                    bw.write(str + "\r\n");
                }
                i++;
            }
            br.close();
            bw.close();
            inputStreamReader.close();
            outputStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
