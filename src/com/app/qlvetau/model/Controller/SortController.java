package com.app.qlvetau.model.Controller;

import com.app.qlvetau.model.dao.HoaDonDAO;
import com.app.qlvetau.model.entity.HoaDon;

import javax.swing.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Controller cho việc sắp xếp hóa đơn
 */
public class SortController {
    private HoaDonDAO hoaDonDAO;
    
    public SortController() {
        this.hoaDonDAO = new HoaDonDAO();
    }
    
    /**
     * Sắp xếp hóa đơn theo họ tên người mua (A-Z)
     */
    public boolean sapXepTheoHoTen() {
        try {
            List<HoaDon> list = hoaDonDAO.getAll();
            
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                        "Chưa có hóa đơn nào!", 
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Sắp xếp theo họ tên
            list.sort(Comparator.comparing(HoaDon::getHoTenNguoiMua));
            
            // Lưu lại
            hoaDonDAO.saveAll(list);
            
            JOptionPane.showMessageDialog(null, 
                    "Sắp xếp theo họ tên thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi xử lý file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Sắp xếp hóa đơn theo số lượng vé (giảm dần)
     */
    public boolean sapXepTheoSoLuong() {
        try {
            List<HoaDon> list = hoaDonDAO.getAll();
            
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                        "Chưa có hóa đơn nào!", 
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            
            // Sắp xếp theo tổng số lượng vé (giảm dần)
            list.sort((hd1, hd2) -> Integer.compare(hd2.getTongSoLuongVe(), hd1.getTongSoLuongVe()));
            
            // Lưu lại
            hoaDonDAO.saveAll(list);
            
            JOptionPane.showMessageDialog(null, 
                    "Sắp xếp theo số lượng vé thành công!", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
            return true;
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi xử lý file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Lấy danh sách hóa đơn hiện tại
     */
    public List<HoaDon> layDanhSachHoaDon() {
        try {
            return hoaDonDAO.getAll();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}