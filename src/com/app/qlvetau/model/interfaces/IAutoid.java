package com.app.qlvetau.model.interfaces;

/**
 * Interface cho các entity có mã tự động tăng
 */
public interface IAutoId {
    int generateId();
    void setId(int id);
    int getId();
}