package com.app.qlvetau.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class để làm việc với file
 */
public class FileUtil {
    
    /**
     * Đọc tất cả các dòng từ file
     */
    public static List<String> readAllLines(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);
        
        if (!file.exists()) {
            return lines;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }
    
    /**
     * Ghi tất cả các dòng vào file
     */
    public static void writeAllLines(String fileName, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
    
    /**
     * Thêm một dòng vào cuối file
     */
    public static void appendLine(String fileName, String line) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        }
    }
    
    /**
     * Kiểm tra file có tồn tại không
     */
    public static boolean fileExists(String fileName) {
        return new File(fileName).exists();
    }
    
    /**
     * Tạo file mới nếu chưa tồn tại
     */
    public static void createFileIfNotExists(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
}