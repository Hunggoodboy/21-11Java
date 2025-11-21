package com.app.qlvetau.model.Controller;

import com.app.qlvetau.model.business.HoaDonCalculator;
import com.app.qlvetau.model.dao.HoaDonDAO;
import com.app.qlvetau.model.dao.NguoiMuaDAO;
import com.app.qlvetau.model.entity.HoaDon;
import com.app.qlvetau.model.entity.NguoiMua;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller cho Báo Cáo
 */
public class BaoCaoController {
    private HoaDonDAO hoaDonDAO;
    private NguoiMuaDAO nguoiMuaDAO;
    private HoaDonCalculator calculator;
    
    public BaoCaoController() {
        this.hoaDonDAO = new HoaDonDAO();
        this.nguoiMuaDAO = new NguoiMuaDAO();
        this.calculator = new HoaDonCalculator();
    }
    
    /**
     * Lập bảng kê thanh toán
     */
    public String lapBangKe() {
        try {
            List<NguoiMua> danhSachNguoiMua = nguoiMuaDAO.getAll();
            List<HoaDon> danhSachHoaDon = hoaDonDAO.getAll();
            
            if (danhSachNguoiMua == null || danhSachNguoiMua.isEmpty()) {
                return "Chưa có dữ liệu người mua!";
            }
            
            if (danhSachHoaDon == null || danhSachHoaDon.isEmpty()) {
                return "Chưa có dữ liệu hóa đơn!";
            }
            
            return calculator.taoBangKe(danhSachNguoiMua, danhSachHoaDon);
            
        } catch (IOException e) {
            return "Lỗi khi đọc file: " + e.getMessage();
        }
    }
    
    /**
     * Lấy danh sách hóa đơn
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
    
    /**
     * Lấy danh sách người mua
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
}