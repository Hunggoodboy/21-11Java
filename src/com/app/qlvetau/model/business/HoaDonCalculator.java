package com.app.qlvetau.model.business;

import com.app.qlvetau.model.entity.HoaDon;
import com.app.qlvetau.model.entity.NguoiMua;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Business logic để tính toán báo cáo thanh toán
 */
public class HoaDonCalculator {
    
    /**
     * Tính tổng tiền cho từng người mua
     * @param danhSachHoaDon danh sách hóa đơn
     * @return Map<MaNguoiMua, TongTien>
     */
    public Map<Integer, Double> tinhTongTienTheoNguoiMua(List<HoaDon> danhSachHoaDon) {
        Map<Integer, Double> ketQua = new HashMap<>();
        
        for (HoaDon hd : danhSachHoaDon) {
            int maNM = hd.getMaNguoiMua();
            double tongTien = hd.calculateTotal();
            
            ketQua.put(maNM, ketQua.getOrDefault(maNM, 0.0) + tongTien);
        }
        
        return ketQua;
    }
    
    /**
     * Tạo bảng kê thanh toán chi tiết
     */
    public String taoBangKe(List<NguoiMua> danhSachNguoiMua, 
                            List<HoaDon> danhSachHoaDon) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Double> tongTienMap = tinhTongTienTheoNguoiMua(danhSachHoaDon);
        
        sb.append("╔════════════════════════════════════════════════════════════════════════════════╗\n");
        sb.append("║                        BẢNG KÊ THANH TOÁN                                      ║\n");
        sb.append("╠════════════════════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-10s ║ %-30s ║ %-15s ║ %-15s ║\n", 
                "Mã NM", "Họ tên", "Số HĐ", "Tổng tiền"));
        sb.append("╠════════════════════════════════════════════════════════════════════════════════╣\n");
        
        double tongTatCa = 0;
        
        for (NguoiMua nm : danhSachNguoiMua) {
            int maNM = nm.getMaNguoiMua();
            if (tongTienMap.containsKey(maNM)) {
                long soHoaDon = danhSachHoaDon.stream()
                        .filter(hd -> hd.getMaNguoiMua() == maNM)
                        .count();
                double tongTien = tongTienMap.get(maNM);
                tongTatCa += tongTien;
                
                sb.append(String.format("║ %05d      ║ %-30s ║ %15d ║ %,15.0f ║\n",
                        maNM, nm.getHoTen(), soHoaDon, tongTien));
            }
        }
        
        sb.append("╠════════════════════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-60s ║ %,15.0f ║\n", 
                "TỔNG CỘNG", tongTatCa));
        sb.append("╚════════════════════════════════════════════════════════════════════════════════╝\n");
        
        return sb.toString();
    }
}