package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.IAutoId;
import com.app.qlvetau.model.interfaces.ICanCalculate;
import com.app.qlvetau.model.interfaces.IFileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class cho Hóa Đơn
 */
public class HoaDon implements IAutoId, IFileEntity, ICanCalculate {
    private static final long serialVersionUID = 1L;
    private static int currentId = 30000; // Bắt đầu từ 30000 (5 chữ số)
    
    private int maHoaDon;
    private int maNguoiMua;
    private String hoTenNguoiMua;
    private List<ChiTietHD> chiTietList;
    
    public HoaDon() {
        this.maHoaDon = generateId();
        this.chiTietList = new ArrayList<>();
    }
    
    public HoaDon(int maNguoiMua, String hoTenNguoiMua) {
        this.maHoaDon = generateId();
        this.maNguoiMua = maNguoiMua;
        this.hoTenNguoiMua = hoTenNguoiMua;
        this.chiTietList = new ArrayList<>();
    }
    
    @Override
    public int generateId() {
        return currentId++;
    }
    
    @Override
    public void setId(int id) {
        this.maHoaDon = id;
        if (id >= currentId) {
            currentId = id + 1;
        }
    }
    
    @Override
    public int getId() {
        return maHoaDon;
    }
    
    public int getMaHoaDon() {
        return maHoaDon;
    }
    
    public void setMaHoaDon(int maHoaDon) {
        setId(maHoaDon);
    }
    
    public int getMaNguoiMua() {
        return maNguoiMua;
    }
    
    public void setMaNguoiMua(int maNguoiMua) {
        this.maNguoiMua = maNguoiMua;
    }
    
    public String getHoTenNguoiMua() {
        return hoTenNguoiMua;
    }
    
    public void setHoTenNguoiMua(String hoTenNguoiMua) {
        this.hoTenNguoiMua = hoTenNguoiMua;
    }
    
    public List<ChiTietHD> getChiTietList() {
        return chiTietList;
    }
    
    public void setChiTietList(List<ChiTietHD> chiTietList) {
        this.chiTietList = chiTietList;
    }
    
    public void addChiTiet(ChiTietHD chiTiet) {
        this.chiTietList.add(chiTiet);
    }
    
    public int getTongSoLuongVe() {
        return chiTietList.stream().mapToInt(ChiTietHD::getSoLuong).sum();
    }
    
    @Override
    public double calculateTotal() {
        return chiTietList.stream().mapToDouble(ChiTietHD::calculateTotal).sum();
    }
    
    @Override
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(maHoaDon).append("|");
        sb.append(maNguoiMua).append("|");
        sb.append(hoTenNguoiMua).append("|");
        sb.append(chiTietList.size()).append("|");
        for (ChiTietHD ct : chiTietList) {
            sb.append(ct.toFileString()).append("|");
        }
        return sb.toString();
    }
    
    @Override
    public void fromFileString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length >= 4) {
            setId(Integer.parseInt(parts[0]));
            this.maNguoiMua = Integer.parseInt(parts[1]);
            this.hoTenNguoiMua = parts[2];
            int soLuongChiTiet = Integer.parseInt(parts[3]);
            
            this.chiTietList = new ArrayList<>();
            for (int i = 0; i < soLuongChiTiet; i++) {
                if (4 + i < parts.length) {
                    ChiTietHD ct = new ChiTietHD();
                    ct.fromFileString(parts[4 + i]);
                    this.chiTietList.add(ct);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("HD: %05d | Mã NM: %05d | Họ tên: %-25s | Số loại vé: %d | Tổng SL: %3d | Tổng tiền: %,15.0f", 
                maHoaDon, maNguoiMua, hoTenNguoiMua, chiTietList.size(), getTongSoLuongVe(), calculateTotal());
    }
    
    public static void resetCurrentId(int id) {
        currentId = id;
    }
}