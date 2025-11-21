package com.app.qlvetau.model.Controller;

import com.app.qlvetau.model.dao.VeDAO;
import com.app.qlvetau.model.entity.Ve;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller cho Vé
 */
public class VeController {
    private VeDAO veDAO;
    
    public VeController() {
        this.veDAO = new VeDAO();
    }
    
    /**
     * Thêm vé mới
     */
    public boolean themVe(String loaiGhe, String donGiaStr) {
        try {
            // Validate
            if (loaiGhe == null || loaiGhe.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Loại ghế không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            double donGia;
            try {
                donGia = Double.parseDouble(donGiaStr);
                if (donGia <= 0) {
                    JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn 0!", 
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Tạo và lưu vé mới
            Ve ve = new Ve(loaiGhe.trim(), donGia);
            veDAO.add(ve);
            
            JOptionPane.showMessageDialog(null, 
                    "Thêm vé thành công!\nMã vé: " + String.format("%05d", ve.getMaVe()), 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi lưu file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Lấy danh sách tất cả vé
     */
    public List<Ve> layDanhSachVe() {
        try {
            return veDAO.getAll();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Tìm vé theo mã
     */
    public Ve timVe(int maVe) {
        try {
            return veDAO.findById(maVe);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Kiểm tra mã vé có tồn tại không
     */
    public boolean kiemTraTonTai(int maVe) {
        try {
            return veDAO.exists(maVe);
        } catch (IOException e) {
            return false;
        }
    }
}