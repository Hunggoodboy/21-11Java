package com.app.qlvetau.model.entity;

import com.app.qlvetau.model.interfaces.IAutoId;
import com.app.qlvetau.model.interfaces.IFileEntity;

/**
 * Entity class cho Người Mua Vé
 */
public class NguoiMua implements IAutoId, IFileEntity {
    private static final long serialVersionUID = 1L;
    private static int currentId = 10000; // Bắt đầu từ 10000 (5 chữ số)
    
    private int maNguoiMua;
    private String hoTen;
    private String diaChi;
    private String loai; // "mua lẻ", "mua tập thể", "mua qua mạng"
    
    public NguoiMua() {
        this.maNguoiMua = generateId();
    }
    
    public NguoiMua(String hoTen, String diaChi, String loai) {
        this.maNguoiMua = generateId();
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.loai = loai;
    }
    
    @Override
    public int generateId() {
        return currentId++;
    }
    
    @Override
    public void setId(int id) {
        this.maNguoiMua = id;
        if (id >= currentId) {
            currentId = id + 1;
        }
    }
    
    @Override
    public int getId() {
        return maNguoiMua;
    }
    
    public int getMaNguoiMua() {
        return maNguoiMua;
    }
    
    public void setMaNguoiMua(int maNguoiMua) {
        setId(maNguoiMua);
    }
    
    public String getHoTen() {
        return hoTen;
    }
    
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
    
    public String getDiaChi() {
        return diaChi;
    }
    
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getLoai() {
        return loai;
    }
    
    public void setLoai(String loai) {
        this.loai = loai;
    }
    
    @Override
    public String toFileString() {
        return maNguoiMua + "|" + hoTen + "|" + diaChi + "|" + loai;
    }
    
    @Override
    public void fromFileString(String data) {
        String[] parts = data.split("\\|");
        if (parts.length >= 4) {
            setId(Integer.parseInt(parts[0]));
            this.hoTen = parts[1];
            this.diaChi = parts[2];
            this.loai = parts[3];
        }
    }
    
    @Override
    public String toString() {
        return String.format("Mã: %05d | Họ tên: %-25s | Địa chỉ: %-30s | Loại: %s", 
                maNguoiMua, hoTen, diaChi, loai);
    }
    
    public static void resetCurrentId(int id) {
        currentId = id;
    }
}