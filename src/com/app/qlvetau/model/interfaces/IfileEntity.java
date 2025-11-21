package com.app.qlvetau.model.interfaces;

import java.io.Serializable;

/**
 * Interface cho các entity có thể lưu vào file
 */
public interface IFileEntity extends Serializable {
    String toFileString();
    void fromFileString(String data);
}