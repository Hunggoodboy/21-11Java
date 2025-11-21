package com.app.qlvetau.model.dao;

import com.app.qlvetau.model.entity.Ve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class cho Ve
 */
public class VeDAO {
    private static final String FILE_NAME = "VE.DAT";
    
    /**
     * Thêm vé mới vào file
     */
    public void add(Ve ve) throws IOException {
        FileUtil.createFileIfNotExists(FILE_NAME);
        FileUtil.appendLine(FILE_NAME, ve.toFileString());
    }
    
    /**
     * Lấy tất cả vé từ file
     */
    public List<Ve> getAll() throws IOException {
        List<Ve> list = new ArrayList<>();
        List<String> lines = FileUtil.readAllLines(FILE_NAME);
        
        int maxId = 20000;
        for (String line : lines) {
            try {
                Ve ve = new Ve();
                ve.fromFileString(line);
                list.add(ve);
                if (ve.getMaVe() >= maxId) {
                    maxId = ve.getMaVe() + 1;
                }
            } catch (Exception e) {
                System.err.println("Lỗi đọc dòng: " + line);
            }
        }
        
        // Reset ID để tránh trùng
        Ve.resetCurrentId(maxId);
        return list;
    }
    
    /**
     * Tìm vé theo mã
     */
    public Ve findById(int maVe) throws IOException {
        List<Ve> list = getAll();
        for (Ve ve : list) {
            if (ve.getMaVe() == maVe) {
                return ve;
            }
        }
        return null;
    }
    
    /**
     * Cập nhật thông tin vé
     */
    public void update(Ve ve) throws IOException {
        List<Ve> list = getAll();
        List<String> lines = new ArrayList<>();
        
        for (Ve v : list) {
            if (v.getMaVe() == ve.getMaVe()) {
                lines.add(ve.toFileString());
            } else {
                lines.add(v.toFileString());
            }
        }
        
        FileUtil.writeAllLines(FILE_NAME, lines);
    }
    
    /**
     * Xóa vé
     */
    public void delete(int maVe) throws IOException {
        List<Ve> list = getAll();
        List<String> lines = new ArrayList<>();
        
        for (Ve v : list) {
            if (v.getMaVe() != maVe) {
                lines.add(v.toFileString());
            }
        }
        
        FileUtil.writeAllLines(FILE_NAME, lines);
    }
    
    /**
     * Kiểm tra mã vé có tồn tại không
     */
    public boolean exists(int maVe) throws IOException {
        return findById(maVe) != null;
    }
}