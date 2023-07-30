package com.google.ssmm.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取文件中的值（环境配置，密码）
 */
public class FileUtils {
    public static final String PASS_FILE = "/opt/ssmm/file";
    public static Map<String,String> getFileValue(String filePath) {
        Map<String,String> result = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":"); // 假设文件中字段是用冒号分隔的，根据实际情况修改分隔符
                if (parts.length == 2) {
                    String fieldName = parts[0].trim();
                    String value = parts[1].trim();
                    result.put(fieldName,value);
                }
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
