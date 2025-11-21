package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.IAutoId;
import com.app.qlvetau.model.interfaces.IFileEntity;

/**
 * Entity class cho Vé Tàu
 */
public class Ve implements IAutoId, IFileEntity {
    private static final long serialVersionUID = 1L;
    private static int currentId = 20000; // Bắt đầu từ 20000 (5 chữ số)
    
    private int maVe;
    private String loaiGhe;
    private double donGia;
    
    public Ve() {
        this.maVe = generateId();
    }
    
    public Ve(String loaiGhe, double donGia) {
        this.maVe = generateId();
        this.loaiGhe = loaiGhe;
        this.donGia = donGia;
    }
    
    @Override
    public int generateId() {
        return currentId++;
    }
    
    @Override
    public void setId(int id) {
        this.maVe = id;
        if (id >= currentId) {
            currentId = id + 1;
        }
    }
    
    @Override
    public int getId() {
        return maVe;
    }
    
    public int getMaVe() {
        return maVe;
    }
    
    public void setMaVe(int maVe) {
        setId(maVe);
    }
    
    public String getLoaiGhe() {
        return loaiGhe;
    }
    
    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }
    
    public double getDonGia() {
        return donGia;
    }
    
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    @Override
    public String toFileString() {
        return maVe + "|" + loaiGhe + "|" + donGia;
    }
    
    @Override
    public void fromFileString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length >= 3) {
            setId(Integer.parseInt(parts[0]));
            this.loaiGhe = parts[1];
            this.donGia = Double.parseDouble(parts[2]);
        }
    }
    
    @Override
    public String toString() {
        return String.format("Mã vé: %05d | Loại ghế: %-20s | Đơn giá: %,.0f VNĐ", 
                maVe, loaiGhe, donGia);
    }
    
    public static void resetCurrentId(int id) {
        currentId = id;
    }
}