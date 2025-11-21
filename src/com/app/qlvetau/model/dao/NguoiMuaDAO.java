package com.app.qlvetau.model.dao;

import com.app.qlvetau.model.entity.NguoiMua;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class cho NguoiMua
 */
public class NguoiMuaDAO {
    private static final String FILE_NAME = "NGUOIMUA.DAT";
    
    /**
     * Thêm người mua mới vào file
     */
    public void add(NguoiMua nguoiMua) throws IOException {
        FileUtil.createFileIfNotExists(FILE_NAME);
        FileUtil.appendLine(FILE_NAME, nguoiMua.toFileString());
    }
    
    /**
     * Lấy tất cả người mua từ file
     */
    public List<NguoiMua> getAll() throws IOException {
        List<NguoiMua> list = new ArrayList<>();
        List<String> lines = FileUtil.readAllLines(FILE_NAME);
        
        int maxId = 10000;
        for (String line : lines) {
            try {
                NguoiMua nm = new NguoiMua();
                nm.fromFileString(line);
                list.add(nm);
                if (nm.getMaNguoiMua() >= maxId) {
                    maxId = nm.getMaNguoiMua() + 1;
                }
            } catch (Exception e) {
                System.err.println("Lỗi đọc dòng: " + line);
            }
        }
        
        // Reset ID để tránh trùng
        NguoiMua.resetCurrentId(maxId);
        return list;
    }
    
    /**
     * Tìm người mua theo mã
     */
    public NguoiMua findById(int maNguoiMua) throws IOException {
        List<NguoiMua> list = getAll();
        for (NguoiMua nm : list) {
            if (nm.getMaNguoiMua() == maNguoiMua) {
                return nm;
            }
        }
        return null;
    }
    
    /**
     * Cập nhật thông tin người mua
     */
    public void update(NguoiMua nguoiMua) throws IOException {
        List<NguoiMua> list = getAll();
        List<String> lines = new ArrayList<>();
        
        for (NguoiMua nm : list) {
            if (nm.getMaNguoiMua() == nguoiMua.getMaNguoiMua()) {
                lines.add(nguoiMua.toFileString());
            } else {
                lines.add(nm.toFileString());
            }
        }
        
        FileUtil.writeAllLines(FILE_NAME, lines);
    }
    
    /**
     * Xóa người mua
     */
    public void delete(int maNguoiMua) throws IOException {
        List<NguoiMua> list = getAll();
        List<String> lines = new ArrayList<>();
        
        for (NguoiMua nm : list) {
            if (nm.getMaNguoiMua() != maNguoiMua) {
                lines.add(nm.toFileString());
            }
        }
        
        FileUtil.writeAllLines(FILE_NAME, lines);
    }
    
    /**
     * Kiểm tra mã người mua có tồn tại không
     */
    public boolean exists(int maNguoiMua) throws IOException {
        return findById(maNguoiMua) != null;
    }
}