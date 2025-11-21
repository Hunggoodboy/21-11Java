package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.ICanCalculate;
import com.app.qlvetau.model.interfaces.IFileEntity;

/**
 * Entity class cho Chi Tiết Hóa Đơn
 */
public class ChiTietHD implements IFileEntity, ICanCalculate {
    private static final long serialVersionUID = 1L;
    
    private int maVe;
    private int soLuong;
    private double donGia;
    private String loaiGhe;
    
    public ChiTietHD() {
    }
    
    public ChiTietHD(int maVe, int soLuong, double donGia, String loaiGhe) {
        this.maVe = maVe;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.loaiGhe = loaiGhe;
    }
    
    public int getMaVe() {
        return maVe;
    }
    
    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public double getDonGia() {
        return donGia;
    }
    
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    public String getLoaiGhe() {
        return loaiGhe;
    }
    
    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }
    
    @Override
    public double calculateTotal() {
        return soLuong * donGia;
    }
    
    @Override
    public String toFileString() {
        return maVe + "," + soLuong + "," + donGia + "," + loaiGhe;
    }
    
    @Override
    public void fromFileString(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 4) {
            this.maVe = Integer.parseInt(parts[0]);
            this.soLuong = Integer.parseInt(parts[1]);
            this.donGia = Double.parseDouble(parts[2]);
            this.loaiGhe = parts[3];
        }
    }
    
    @Override
    public String toString() {
        return String.format("Mã vé: %05d | Loại ghế: %-20s | SL: %2d | Đơn giá: %,10.0f | Thành tiền: %,15.0f", 
                maVe, loaiGhe, soLuong, donGia, calculateTotal());
    }
}