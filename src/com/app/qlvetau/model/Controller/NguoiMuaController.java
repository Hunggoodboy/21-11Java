package com.app.qlvetau.model.Controller;

import com.app.qlvetau.model.dao.NguoiMuaDAO;
import com.app.qlvetau.model.entity.NguoiMua;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller cho Người Mua
 */
public class NguoiMuaController {
    private NguoiMuaDAO nguoiMuaDAO;
    
    public NguoiMuaController() {
        this.nguoiMuaDAO = new NguoiMuaDAO();
    }
    
    /**
     * Thêm người mua mới
     */
    public boolean themNguoiMua(String hoTen, String diaChi, String loai) {
        try {
            // Validate
            if (hoTen == null || hoTen.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Họ tên không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (diaChi == null || diaChi.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (loai == null || loai.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Loại không được để trống!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Tạo và lưu người mua mới
            NguoiMua nm = new NguoiMua(hoTen.trim(), diaChi.trim(), loai.trim());
            nguoiMuaDAO.add(nm);
            
            JOptionPane.showMessageDialog(null, 
                    "Thêm người mua thành công!\nMã người mua: " + String.format("%05d", nm.getMaNguoiMua()), 
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
     * Lấy danh sách tất cả người mua
     */
    public List<NguoiMua> layDanhSachNguoiMua() {
        try {
            return nguoiMuaDAO.getAll();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Tìm người mua theo mã
     */
    public NguoiMua timNguoiMua(int maNguoiMua) {
        try {
            return nguoiMuaDAO.findById(maNguoiMua);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Kiểm tra mã người mua có tồn tại không
     */
    public boolean kiemTraTonTai(int maNguoiMua) {
        try {
            return nguoiMuaDAO.exists(maNguoiMua);
        } catch (IOException e) {
            return false;
        }
    }
}