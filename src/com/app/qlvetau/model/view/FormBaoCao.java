package com.app.qlvetau.model.view;

import com.app.qlvetau.model.Controller.BaoCaoController;

import javax.swing.*;
import java.awt.*;

/**
 * Form Báo Cáo - Bảng kê thanh toán
 */
public class FormBaoCao extends JFrame {
    private BaoCaoController controller;
    private JTextArea txtBaoCao;
    
    public FormBaoCao() {
        controller = new BaoCaoController();
        
        setTitle("BẢNG KÊ THANH TOÁN");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initComponents();
        loadBaoCao();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));
        
        // Panel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnTaiLai = new JButton("Tải lại báo cáo");
        btnTaiLai.addActionListener(e -> loadBaoCao());
        buttonPanel.add(btnTaiLai);
        
        // Text area để hiển thị báo cáo
        txtBaoCao = new JTextArea();
        txtBaoCao.setEditable(false);
        txtBaoCao.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(txtBaoCao);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Bảng Kê Thanh Toán"));
        
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void loadBaoCao() {
        String baoCao = controller.lapBangKe();
        txtBaoCao.setText(baoCao);
    }
}