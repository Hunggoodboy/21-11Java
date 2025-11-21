package com.app.qlvetau.model.Controller;

import com.app.qlvetau.model.dao.HoaDonDAO;
import com.app.qlvetau.model.dao.NguoiMuaDAO;
import com.app.qlvetau.model.dao.VeDAO;
import com.app.qlvetau.model.entity.ChiTietHD;
import com.app.qlvetau.model.entity.HoaDon;
import com.app.qlvetau.model.entity.NguoiMua;
import com.app.qlvetau.model.entity.Ve;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller cho Hóa Đơn
 */
public class HoaDonController {
    private HoaDonDAO hoaDonDAO;
    private NguoiMuaDAO nguoiMuaDAO;
    private VeDAO veDAO;
    
    public HoaDonController() {
        this.hoaDonDAO = new HoaDonDAO();
        this.nguoiMuaDAO = new NguoiMuaDAO();
        this.veDAO = new VeDAO();
    }
    
    /**
     * Tạo hóa đơn mới
     */
    public HoaDon taoHoaDon(int maNguoiMua) {
        try {
            NguoiMua nm = nguoiMuaDAO.findById(maNguoiMua);
            if (nm == null) {
                JOptionPane.showMessageDialog(null, 
                        "Không tìm thấy người mua với mã: " + maNguoiMua, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            return new HoaDon(maNguoiMua, nm.getHoTen());
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    /**
     * Thêm chi tiết vào hóa đơn
     */
    public boolean themChiTiet(HoaDon hoaDon, int maVe, int soLuong) {
        try {
            // Validate số lượng
            if (soLuong <= 0 || soLuong > 20) {
                JOptionPane.showMessageDialog(null, 
                        "Số lượng phải từ 1 đến 20!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Kiểm tra số loại vé (không quá 4)
            if (hoaDon.getChiTietList().size() >= 4) {
                JOptionPane.showMessageDialog(null, 
                        "Một hóa đơn không được mua quá 4 loại vé!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Tìm vé
            Ve ve = veDAO.findById(maVe);
            if (ve == null) {
                JOptionPane.showMessageDialog(null, 
                        "Không tìm thấy vé với mã: " + maVe, 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            // Kiểm tra vé đã tồn tại trong hóa đơn chưa
            for (ChiTietHD ct : hoaDon.getChiTietList()) {
                if (ct.getMaVe() == maVe) {
                    JOptionPane.showMessageDialog(null, 
                            "Vé này đã có trong hóa đơn!", 
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            
            // Thêm chi tiết
            ChiTietHD chiTiet = new ChiTietHD(maVe, soLuong, ve.getDonGia(), ve.getLoaiGhe());
            hoaDon.addChiTiet(chiTiet);
            
            return true;
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    /**
     * Lưu hóa đơn
     */
    public boolean luuHoaDon(HoaDon hoaDon) {
        try {
            if (hoaDon.getChiTietList().isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                        "Hóa đơn phải có ít nhất một loại vé!", 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            hoaDonDAO.add(hoaDon);
            JOptionPane.showMessageDialog(null, 
                    "Lưu hóa đơn thành công!\nMã hóa đơn: " + String.format("%05d", hoaDon.getMaHoaDon()), 
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
     * Lấy danh sách tất cả hóa đơn
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
     * Tìm hóa đơn theo mã người mua
     */
    public List<HoaDon> timHoaDonTheoNguoiMua(int maNguoiMua) {
        try {
            return hoaDonDAO.findByMaNguoiMua(maNguoiMua);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, 
                    "Lỗi khi đọc file: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}