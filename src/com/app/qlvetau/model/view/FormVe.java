package com.app.qlvetau.model.view;

import com.app.qlvetau.model.Controller.VeController;
import com.app.qlvetau.model.entity.Ve;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Form quản lý Vé
 */
public class FormVe extends JFrame {
    private VeController controller;
    private JTextField txtLoaiGhe, txtDonGia;
    private JTable table;
    private DefaultTableModel tableModel;
    
    public FormVe() {
        controller = new VeController();
        
        setTitle("QUẢN LÝ VÉ TÀU");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initComponents();
        loadData();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông Tin Vé"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Loại ghế
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Loại ghế:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        txtLoaiGhe = new JTextField(30);
        inputPanel.add(txtLoaiGhe, gbc);
        
        // Đơn giá
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        inputPanel.add(new JLabel("Đơn giá (VNĐ):"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        txtDonGia = new JTextField(30);
        inputPanel.add(txtDonGia, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnThem = new JButton("Thêm");
        JButton btnLamMoi = new JButton("Làm mới");
        JButton btnTaiLai = new JButton("Tải lại danh sách");
        
        btnThem.addActionListener(e -> themVe());
        btnLamMoi.addActionListener(e -> lamMoi());
        btnTaiLai.addActionListener(e -> loadData());
        
        buttonPanel.add(btnThem);
        buttonPanel.add(btnLamMoi);
        buttonPanel.add(btnTaiLai);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);
        
        // Table
        String[] columns = {"Mã Vé", "Loại Ghế", "Đơn Giá"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Vé"));
        
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void themVe() {
        String loaiGhe = txtLoaiGhe.getText().trim();
        String donGia = txtDonGia.getText().trim();
        
        if (controller.themVe(loaiGhe, donGia)) {
            lamMoi();
            loadData();
        }
    }
    
    private void lamMoi() {
        txtLoaiGhe.setText("");
        txtDonGia.setText("");
        txtLoaiGhe.requestFocus();
    }
    
    private void loadData() {
        tableModel.setRowCount(0);
        List<Ve> list = controller.layDanhSachVe();
        
        if (list != null) {
            for (Ve ve : list) {
                tableModel.addRow(new Object[]{
                    String.format("%05d", ve.getMaVe()),
                    ve.getLoaiGhe(),
                    String.format("%,.0f", ve.getDonGia())
                });
            }
        }
    }
}