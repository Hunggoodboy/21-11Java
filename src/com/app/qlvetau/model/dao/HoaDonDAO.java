package com.app.qlvetau.model.dao;

import com.app.qlvetau.model.entity.HoaDon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class cho HoaDon
 */
public class HoaDonDAO {
    private static final String FILE_NAME = "HOADON.DAT";
    
    /**
     * Thêm hóa đơn mới vào file
     */
    public void add(HoaDon hoaDon) throws IOException {
        FileUtil.createFileIfNotExists(FILE_NAME);
        FileUtil.appendLine(FILE_NAME, hoaDon.toFileString());
    }
    
    /**
     * Lấy tất cả hóa đơn từ file
     */
    public List<HoaDon> getAll() throws IOException {
        List<HoaDon> list = new ArrayList<>();
        List<String> lines = FileUtil.readAllLines(FILE_NAME);
        
        int maxId = 30000;
        for (String line : lines) {
            try {
                HoaDon hd = new HoaDon();
                hd.fromFileString(line);
                list.add(hd);
                if (hd.getMaHoaDon() >= maxId) {
                    maxId = hd.getMaHoaDon() + 1;
                }
            } catch (Exception e) {
                System.err.println("Lỗi đọc dòng: " + line);
                e.printStackTrace();
            }
        }
        
        // Reset ID để tránh trùng
        HoaDon.resetCurrentId(maxId);
        return list;
    }
    
    /**
     * Tìm hóa đơn theo mã
     */
    public HoaDon findById(int maHoaDon) throws IOException {
        List<HoaDon> list = getAll();
        for (HoaDon hd : list) {
            if (hd.getMaHoaDon() == maHoaDon) {
                return hd;
            }
        }
        return null;
    }
    
    /**
     * Tìm tất cả hóa đơn của một người mua
     */
    public List<HoaDon> findByMaNguoiMua(int maNguoiMua) throws IOException {
        List<HoaDon> result = new ArrayList<>();
        List<HoaDon> list = getAll();
        
        for (HoaDon hd : list) {
            if (hd.getMaNguoiMua() == maNguoiMua) {
                result.add(hd);
            }
        }
        
        return result;
    }
    
    /**
     * Cập nhật hóa đơn
     */
    public void update(HoaDon hoaDon) throws IOException {
        List<HoaDon> list = getAll();
        List<String> lines = new ArrayList<>();
        
        for (HoaDon hd : list) {
            if (hd.getMaHoaDon() == hoaDon.getMaHoaDon()) {
                lines.add(hoaDon.toFileString());
            } else {
                lines.add(hd.toFileString());
            }
        }
        
        FileUtil.writeAllLines(FILE_NAME, lines);
    }
    
    /**
     * Xóa hóa đơn
     */
    public void delete(int maHoaDon) throws IOException {
        List<HoaDon> list = getAll();
        List<String> lines = new ArrayList<>();
        
        for (HoaDon hd : list) {
            if (hd.getMaHoaDon() != maHoaDon) {
                lines.add(hd.toFileString());
            }
        }
        
        FileUtil.writeAllLines(FILE_NAME, lines);
    }
    
    /**
     * Lưu tất cả hóa đơn vào file (dùng cho sắp xếp)
     */
    public void saveAll(List<HoaDon> list) throws IOException {
        List<String> lines = new ArrayList<>();
        for (HoaDon hd : list) {
            lines.add(hd.toFileString());
        }
        FileUtil.writeAllLines(FILE_NAME, lines);
    }
}